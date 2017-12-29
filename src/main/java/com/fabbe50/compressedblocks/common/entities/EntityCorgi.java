package com.fabbe50.compressedblocks.common.entities;

import com.fabbe50.compressedblocks.common.entities.ai.EntityAIBegDecoy;
import com.fabbe50.compressedblocks.core.lib.EnumCorgiType;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.*;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;

/**
 * Created by fabbe50 on 19/06/2016.
 */
public class EntityCorgi extends EntityTameable {
    private static final DataParameter<Float> DATA_HEALTH_ID = EntityDataManager.<Float>createKey(EntityCorgi.class, DataSerializers.FLOAT);
    private static final DataParameter<Boolean> BEGGING = EntityDataManager.<Boolean>createKey(EntityCorgi.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> COLLARCOLOR = EntityDataManager.<Integer>createKey(EntityCorgi.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> CORGITYPE = EntityDataManager.<Integer>createKey(EntityCorgi.class, DataSerializers.VARINT);

    private float headRotation;
    private float headRotationOld;

    private boolean isWet;

    private boolean isShaking;

    private float timeCorgiIsShaking;
    private float prevTimeCorgiIsShaking;

    public EntityCorgi(World worldIn) {
        super(worldIn);
        this.setSize(0.6875f, 0.5625f);
        this.setTamed(false);
    }

    protected void initEntityAI() {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIBegDecoy(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<EntityAnimal>(this, EntityAnimal.class, false, new Predicate<Entity>() {
            public boolean apply(@Nullable Entity entity) {
                return entity instanceof EntityPig;
            }
        }));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);

        if (this.isTamed()) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        }
        else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        }

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn) {
        super.setAttackTarget(entitylivingbaseIn);

        if (entitylivingbaseIn == null) {
            this.setAngry(false);
        }
        else if (!this.isTamed()) {
            this.setAngry(true);
        }
    }

    protected void updateAITasks() {
        this.dataManager.set(DATA_HEALTH_ID, this.getHealth());
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DATA_HEALTH_ID, this.getHealth());
        this.dataManager.register(BEGGING, false);
        this.dataManager.register(COLLARCOLOR, EnumDyeColor.RED.getDyeDamage());
        this.dataManager.register(CORGITYPE, EnumCorgiType.byDamage(rand.nextInt(8)).getID());
    }

    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    public static void registerFixesCorgi(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntityCorgi.class);
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Angry", this.isAngry());
        compound.setByte("CollarColor", (byte)this.getCollarColor().getDyeDamage());
        compound.setByte("CorgiType", (byte)this.getCorgiType().getID());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setAngry(compound.getBoolean("Angry"));

        if (compound.hasKey("CollarColor", 99)) {
            this.setCollarColor(EnumDyeColor.byDyeDamage(compound.getByte("CollarColor")));
        }
        if (compound.hasKey("CorgiType", 99)) {
            this.setCorgitype(EnumCorgiType.byDamage(compound.getByte("CorgiType")));
        }
    }

    protected SoundEvent getAmbientSound() {
        return this.isAngry() ? SoundEvents.ENTITY_WOLF_GROWL : (this.rand.nextInt(3) == 0 ? (this.isTamed() && this.dataManager.get(DATA_HEALTH_ID) < 10.0F ? SoundEvents.ENTITY_WOLF_WHINE : SoundEvents.ENTITY_WOLF_PANT) : SoundEvents.ENTITY_WOLF_AMBIENT);
    }

    protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_WOLF_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WOLF_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return null;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.world.isRemote && this.isWet && !this.isShaking && !this.hasPath() && this.onGround) {
            this.isShaking = true;
            this.timeCorgiIsShaking = 0.0F;
            this.prevTimeCorgiIsShaking = 0.0F;
            this.world.setEntityState(this, (byte)8);
        }

        if (!this.world.isRemote && this.getAttackTarget() == null && this.isAngry()) {
            this.setAngry(false);
        }
    }

    public void onUpdate() {
        super.onUpdate();
        this.headRotationOld = this.headRotation;

        if (this.isBegging()) {
            this.headRotation += (1.0F - this.headRotation) * 0.4F;
        }
        else {
            this.headRotation += (0.0F - this.headRotation) * 0.4F;
        }

        if (this.isWet()) {
            this.isWet = true;
            this.isShaking = false;
            this.timeCorgiIsShaking = 0.0F;
            this.prevTimeCorgiIsShaking = 0.0F;
        }
        else if ((this.isWet || this.isShaking) && this.isShaking) {
            if (this.timeCorgiIsShaking == 0.0F) {
                this.playSound(SoundEvents.ENTITY_WOLF_SHAKE, this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeCorgiIsShaking = this.timeCorgiIsShaking;
            this.timeCorgiIsShaking += 0.05F;

            if (this.prevTimeCorgiIsShaking >= 2.0F) {
                this.isWet = false;
                this.isShaking = false;
                this.prevTimeCorgiIsShaking = 0.0F;
                this.timeCorgiIsShaking = 0.0F;
            }

            if (this.timeCorgiIsShaking > 0.4F) {
                float f = (float)this.getEntityBoundingBox().minY;
                int i = (int)(MathHelper.sin((this.timeCorgiIsShaking - 0.4F) * (float)Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.world.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + (double)f1, (double)(f + 0.8F), this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ, new int[0]);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean isCorgiWet() {
        return this.isWet;
    }

    @SideOnly(Side.CLIENT)
    public float getShadingWhileWet(float shade) {
        return 0.75F + (this.prevTimeCorgiIsShaking + (this.timeCorgiIsShaking - this.prevTimeCorgiIsShaking) * shade) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float f2, float f3) {
        float f = (this.prevTimeCorgiIsShaking + (this.timeCorgiIsShaking - this.prevTimeCorgiIsShaking) * f2 + f3) / 1.8F;

        if (f < 0.0F) {
            f = 0.0F;
        }
        else if (f > 1.0F) {
            f = 1.0F;
        }

        return MathHelper.sin(f * (float)Math.PI) * MathHelper.sin(f * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float angle) {
        return (this.headRotationOld + (this.headRotation - this.headRotationOld) * angle) * 0.15F * (float)Math.PI;
    }

    public float getEyeHeight() {
        return this.height * 0.9F;
    }

    public int getVerticalFaceSpeed() {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        else {
            Entity entity = source.getImmediateSource();

            if (this.aiSit != null) {
                this.aiSit.setSitting(false);
            }

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    public boolean attackEntityAsMob(Entity entityIn) {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()));

        if (flag) {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
        }
        else {
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
        }

        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (this.isTamed()) {
            if (stack != null) {
                if (stack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood)stack.getItem();
                    if (itemfood == Items.BAKED_POTATO && (Float)this.dataManager.get(DATA_HEALTH_ID) < 60.0) {
                        if (!player.capabilities.isCreativeMode) {
                            stack.setCount(stack.getCount() - 1);
                        }

                        this.heal((float)itemfood.getHealAmount(stack));
                        return true;
                    }
                }
                else if (stack.getItem() == Items.DYE) {
                    EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());
                    if (enumdyecolor != this.getCollarColor()) {
                        this.setCollarColor(enumdyecolor);
                        if (!player.capabilities.isCreativeMode) {
                            stack.setCount(stack.getCount() - 1);
                        }

                        return true;
                    }
                }
            }
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(stack)) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else if (stack != null && stack.getItem() == Items.BAKED_POTATO && !this.isAngry()) {
            if (!player.capabilities.isCreativeMode) {
                stack.setCount(stack.getCount() - 1);
            }
            if (!this.world.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.navigator.clearPath();
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(60.0F);
                    this.setOwnerId(player.getUniqueID());
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7);
                }
                else {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }
            return true;
        }
        return super.processInteract(player, hand);
    }

    public int getMaxSpawnedInChunk() {
        return 0;
    }

    public boolean isAngry() {
        return (this.dataManager.get(TAMED) & 2) != 0;
    }

    public void setAngry(boolean angry) {
        byte b0 = this.dataManager.get(TAMED);

        if (angry) {
            this.dataManager.set(TAMED, (byte) (b0 | 2));
        }
        else {
            this.dataManager.set(TAMED, (byte) (b0 & -3));
        }
    }

    public EnumCorgiType getCorgiType() {
        return EnumCorgiType.byDamage(this.dataManager.get(CORGITYPE));
    }

    public void setCorgitype(EnumCorgiType corgitype) {
        this.dataManager.set(CORGITYPE, corgitype.getID());
    }

    public EnumDyeColor getCollarColor() {
        return EnumDyeColor.byDyeDamage(this.dataManager.get(COLLARCOLOR) & 15);
    }

    public void setCollarColor(EnumDyeColor collarcolor) {
        this.dataManager.set(COLLARCOLOR, collarcolor.getDyeDamage());
    }

    public EnumCorgiType getRandomCorgiType(Random rand) {
        int i = rand.nextInt(EnumCorgiType.count());
        return EnumCorgiType.byDamage(i);
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setCorgitype(getRandomCorgiType(this.world.rand));
        return livingdata;
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        return this.isAngry() ? 1.5393804F : (this.isTamed() ? (0.55F - (this.getMaxHealth() - this.dataManager.get(DATA_HEALTH_ID)) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F));
    }

    @SuppressWarnings("NullableProblems")
    public EntityCorgi createChild(EntityAgeable ageable) {
        EntityCorgi entitycorgi = new EntityCorgi(this.world);
        UUID uuid = this.getOwnerId();

        if (uuid != null) {
            entitycorgi.setOwnerId(uuid);
            entitycorgi.setTamed(true);
        }

        return entitycorgi;
    }

    public void setBegging(boolean beg) {
        this.dataManager.set(BEGGING, beg);
    }

    public boolean isBegging() {
        return this.dataManager.get(BEGGING);
    }

    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        }
        else if (!this.isTamed()) {
            return false;
        }
        else if (!(otherAnimal instanceof EntityCorgi)) {
            return false;
        }
        else {
            EntityCorgi entityCorgi = (EntityCorgi)otherAnimal;
            return entityCorgi.isTamed() && (!entityCorgi.isSitting() && (this.isInLove() && entityCorgi.isInLove()));
        }
    }
}
