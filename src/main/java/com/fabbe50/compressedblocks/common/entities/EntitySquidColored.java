package com.fabbe50.compressedblocks.common.entities;

import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.fabbe50.compressedblocks.core.registry.LootTableRegistry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by fabbe on 28/05/2017.
 */
public class EntitySquidColored extends EntityWaterMob {
    private static final DataParameter<Byte> SQUIDCOLOR = EntityDataManager.<Byte>createKey(EntitySquidColored.class, DataSerializers.BYTE);
    public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    /** appears to be rotation in radians; we already have pitch & yaw, so this completes the triumvirate. */
    public float squidRotation;
    /** previous squidRotation in radians */
    public float prevSquidRotation;
    /** angle of the tentacles in radians */
    public float tentacleAngle;
    /** the last calculated angle of the tentacles in radians */
    public float lastTentacleAngle;
    private float randomMotionSpeed;
    /** change in squidRotation in radians. */
    private float rotationVelocity;
    private float rotateSpeed;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public EntitySquidColored(World worldIn) {
        super(worldIn);
        this.setSize(0.8F, 0.8F);
        this.rand.setSeed((long)(1 + this.getEntityId()));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    public static void registerFixesSquidColored(DataFixer fixer) {
        EntityLiving.registerFixesMob(fixer, EntitySquidColored.class);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntitySquidColored.AIMoveRandom(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    @Override
    public float getEyeHeight() {
        return this.height * 0.5F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_SQUID_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SQUID_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(SQUIDCOLOR, (byte) 0);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_SQUID;
    }

    /*
    @Nullable
    protected ResourceLocation getLootTable() {
        switch (this.getSquidColor()) {
            case WHITE:
            default:
                return LootTableRegistry.ENTITIES_SQUID_WHITE;
            case ORANGE:
                return LootTableRegistry.ENTITIES_SQUID_ORANGE;
            case MAGENTA:
                return LootTableRegistry.ENTITIES_SQUID_MAGENTA;
            case LIGHT_BLUE:
                return LootTableRegistry.ENTITIES_SQUID_LIGHT_BLUE;
            case YELLOW:
                return LootTableRegistry.ENTITIES_SQUID_YELLOW;
            case LIME:
                return LootTableRegistry.ENTITIES_SQUID_LIME;
            case PINK:
                return LootTableRegistry.ENTITIES_SQUID_PINK;
            case GRAY:
                return LootTableRegistry.ENTITIES_SQUID_GRAY;
            case SILVER:
                return LootTableRegistry.ENTITIES_SQUID_SILVER;
            case CYAN:
                return LootTableRegistry.ENTITIES_SQUID_CYAN;
            case PURPLE:
                return LootTableRegistry.ENTITIES_SQUID_PURPLE;
            case BLUE:
                return LootTableRegistry.ENTITIES_SQUID_BLUE;
            case BROWN:
                return LootTableRegistry.ENTITIES_SQUID_BROWN;
            case GREEN:
                return LootTableRegistry.ENTITIES_SQUID_GREEN;
            case RED:
                return LootTableRegistry.ENTITIES_SQUID_RED;
            case BLACK:
                return LootTableRegistry.ENTITIES_SQUID_BLACK;
        }
    }*/

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        super.processInteract(player, hand);
        if (player.getHeldItem(hand).getItem() == Items.GLASS_BOTTLE) {
            player.getHeldItem(hand).shrink(1);
            if (player.getHeldItem(hand).isEmpty()) {
                player.setHeldItem(hand, new ItemStack(ItemRegistry.INKBOTTLE, 1, getSquidColor().getDyeDamage()));
            } else if (!player.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET))) {
                player.dropItem(new ItemStack(ItemRegistry.INKBOTTLE, 1, getSquidColor().getDyeDamage()), false);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.prevSquidRotation = this.squidRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.squidRotation += this.rotationVelocity;

        if ((double)this.squidRotation > (Math.PI * 2D)) {
            if (this.world.isRemote) {
                this.squidRotation = ((float)Math.PI * 2F);
            }
            else {
                this.squidRotation = (float)((double)this.squidRotation - (Math.PI * 2D));

                if (this.rand.nextInt(10) == 0) {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.setEntityState(this, (byte)19);
            }
        }

        if (this.inWater) {
            if (this.squidRotation < (float)Math.PI) {
                float f = this.squidRotation / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float)Math.PI) * (float)Math.PI * 0.25F;

                if ((double)f > 0.75D) {
                    this.randomMotionSpeed = 1.0F;
                    this.rotateSpeed = 1.0F;
                }
                else {
                    this.rotateSpeed *= 0.8F;
                }
            }
            else {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.world.isRemote) {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }

            float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)MathHelper.atan2(this.motionX, this.motionZ)) * (180F / (float)Math.PI) - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.squidYaw = (float)((double)this.squidYaw + Math.PI * (double)this.rotateSpeed * 1.5D);
            this.squidPitch += (-((float)MathHelper.atan2((double)f1, this.motionY)) * (180F / (float)Math.PI) - this.squidPitch) * 0.1F;
        }
        else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float)Math.PI * 0.25F;

            if (!this.world.isRemote) {
                this.motionX = 0.0D;
                this.motionZ = 0.0D;

                if (this.isPotionActive(MobEffects.LEVITATION)) {
                    this.motionY += 0.05D * (double)(this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY;
                }
                else if (!this.hasNoGravity()) {
                    this.motionY -= 0.08D;
                }

                this.motionY *= 0.9800000190734863D;
            }

            this.squidPitch = (float)((double)this.squidPitch + (double)(-90.0F - this.squidPitch) * 0.02D);
        }
    }

    private EnumDyeColor getSquidColor() {
        return EnumDyeColor.byMetadata((Byte) this.dataManager.get(SQUIDCOLOR) & 15);
    }

    private void setSquidColor(EnumDyeColor color) {
        byte b0 = (Byte) this.dataManager.get(SQUIDCOLOR);
        this.dataManager.set(SQUIDCOLOR, (byte) (b0 & 240 | color.getMetadata() & 15));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setByte("Color", (byte)this.getSquidColor().getMetadata());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setSquidColor(EnumDyeColor.byMetadata(compound.getByte("Color")));
    }

    private static EnumDyeColor getRandomSquidColor(Random random) {
        return EnumDyeColor.byDyeDamage(random.nextInt(16));
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setSquidColor(getRandomSquidColor(this.world.rand));
        return livingdata;
    }

    @Override
    public void moveEntityWithHeading(float strafe, float forward) {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY > 45.0D && this.posY < (double)this.world.getSeaLevel() && super.getCanSpawnHere();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 19) {
            this.squidRotation = 0.0F;
        }
        else {
            super.handleStatusUpdate(id);
        }
    }

    public void setMovementVector(float randomMotionVecXIn, float randomMotionVecYIn, float randomMotionVecZIn) {
        this.randomMotionVecX = randomMotionVecXIn;
        this.randomMotionVecY = randomMotionVecYIn;
        this.randomMotionVecZ = randomMotionVecZIn;
    }

    public boolean hasMovementVector() {
        return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
    }

    static class AIMoveRandom extends EntityAIBase {
        private final EntitySquidColored squid;

        public AIMoveRandom(EntitySquidColored p_i45859_1_) {
            this.squid = p_i45859_1_;
        }

        public boolean shouldExecute() {
            return true;
        }

        public void updateTask() {
            int i = this.squid.getAge();

            if (i > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            }
            else if (this.squid.getRNG().nextInt(50) == 0 || !this.squid.inWater || !this.squid.hasMovementVector()) {
                float f = this.squid.getRNG().nextFloat() * ((float)Math.PI * 2F);
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
            }
        }
    }
}
