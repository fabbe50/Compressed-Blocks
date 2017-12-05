package com.fabbe50.compressedblocks.common.entities;

import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Random;

/**
 * Created by fabbe on 28/11/2017 - 3:32 PM.
 */
public class EntityColoredSquid extends EntityWaterMob {
    private static final DataParameter<Byte> DYE_COLOR = EntityDataManager.createKey(EntityColoredSquid.class, DataSerializers.BYTE);
    private static final Map<EnumDyeColor, float[]> DYE_TO_RGB = Maps.newEnumMap(EnumDyeColor.class);

    public float squidPitch;
    public float prevSquidPitch;
    public float squidYaw;
    public float prevSquidYaw;
    public float squidRotation;
    public float prevSquidRotation;
    public float tentacleAngle;
    public float lastTentacleAngle;
    private float randomMotionSpeed;
    private float rotationVelocity;
    private float rotateSpeed;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public EntityColoredSquid(World worldIn) {
        super(worldIn);
        this.setSize(0.8F, 0.8F);
        this.rand.setSeed((long) (1 + this.getEntityId()));
        this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    //Colorstuff
    public static float[] getDyeRgb(EnumDyeColor dyeColor) {
        return (float[])DYE_TO_RGB.get(dyeColor);
    }

    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setBoolean("Sheared", this.getInked());
        compound.setByte("Color", (byte)this.getInkColor().getMetadata());
    }

    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setInked(compound.getBoolean("Sheared"));
        this.setInkColor(EnumDyeColor.byMetadata(compound.getByte("Color")));
    }

    public EnumDyeColor getInkColor() {
        return EnumDyeColor.byMetadata(((Byte)this.dataManager.get(DYE_COLOR)).byteValue() & 15);
    }

    public void setInkColor(EnumDyeColor color) {
        byte b0 = ((Byte)this.dataManager.get(DYE_COLOR)).byteValue();
        this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 & 240 | color.getMetadata() & 15)));
    }

    public boolean getInked() {
        return (((Byte)this.dataManager.get(DYE_COLOR)).byteValue() & 16) != 0;
    }

    public void setInked(boolean sheared) {
        byte b0 = ((Byte)this.dataManager.get(DYE_COLOR)).byteValue();

        if (sheared) {
            this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 | 16)));
        }
        else {
            this.dataManager.set(DYE_COLOR, Byte.valueOf((byte)(b0 & -17)));
        }
    }

    public static EnumDyeColor getRandomSquidColor(Random random) {
        return EnumDyeColor.byDyeDamage(random.nextInt(15));
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        this.setInkColor(getRandomSquidColor(this.world.rand));
        return livingdata;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (stack.getItem() == ItemRegistry.INK_EXTRACTOR && this.isEntityAlive()) {
            if (player.inventory.hasItemStack(new ItemStack(Items.GLASS_BOTTLE)) || player.capabilities.isCreativeMode) {
                setInked(true);
                if (!player.capabilities.isCreativeMode)
                    player.inventory.decrStackSize(player.inventory.getSlotFor(new ItemStack(Items.GLASS_BOTTLE)), 1);
                player.inventory.addItemStackToInventory(new ItemStack(ItemRegistry.INKBOTTLE, 1, this.getInkColor().getDyeDamage()));
                this.damageEntity(DamageSource.causePlayerDamage(player), rand.nextInt(3));
                this.performHurtAnimation();
                stack.damageItem(1, player);
                return true;
            }
        }
        return false;
    }

    static {
        DYE_TO_RGB.put(EnumDyeColor.WHITE, new float[] {1.0F, 1.0F, 1.0F});
        DYE_TO_RGB.put(EnumDyeColor.ORANGE, new float[] {0.85F, 0.5F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.MAGENTA, new float[] {0.7F, 0.3F, 0.85F});
        DYE_TO_RGB.put(EnumDyeColor.LIGHT_BLUE, new float[] {0.4F, 0.6F, 0.85F});
        DYE_TO_RGB.put(EnumDyeColor.YELLOW, new float[] {0.9F, 0.9F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.LIME, new float[] {0.5F, 0.8F, 0.1F});
        DYE_TO_RGB.put(EnumDyeColor.PINK, new float[] {0.95F, 0.5F, 0.65F});
        DYE_TO_RGB.put(EnumDyeColor.GRAY, new float[] {0.3F, 0.3F, 0.3F});
        DYE_TO_RGB.put(EnumDyeColor.SILVER, new float[] {0.6F, 0.6F, 0.6F});
        DYE_TO_RGB.put(EnumDyeColor.CYAN, new float[] {0.3F, 0.5F, 0.6F});
        DYE_TO_RGB.put(EnumDyeColor.PURPLE, new float[] {0.5F, 0.25F, 0.7F});
        DYE_TO_RGB.put(EnumDyeColor.BLUE, new float[] {0.2F, 0.3F, 0.7F});
        DYE_TO_RGB.put(EnumDyeColor.BROWN, new float[] {0.4F, 0.3F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.GREEN, new float[] {0.4F, 0.5F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.RED, new float[] {0.6F, 0.2F, 0.2F});
        DYE_TO_RGB.put(EnumDyeColor.BLACK, new float[] {0.1F, 0.1F, 0.1F});
    }

    //Squidbrains
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityColoredSquid.AIMoveRandom(this));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(DYE_COLOR, Byte.valueOf((byte)0));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
    }

    public float getEyeHeight() {
        return this.height * 0.5F;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SQUID_AMBIENT;
    }

    protected SoundEvent getHurtSound() {
        return SoundEvents.ENTITY_SQUID_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SQUID_DEATH;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return null;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.prevSquidPitch = this.squidPitch;
        this.prevSquidYaw = this.squidYaw;
        this.prevSquidRotation = this.squidRotation;
        this.lastTentacleAngle = this.tentacleAngle;
        this.squidRotation += this.rotationVelocity;

        if ((double) this.squidRotation > (Math.PI * 2D)) {
            if (this.world.isRemote) {
                this.squidRotation = ((float) Math.PI * 2F);
            } else {
                this.squidRotation = (float) ((double) this.squidRotation - (Math.PI * 2D));

                if (this.rand.nextInt(10) == 0) {
                    this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
                }

                this.world.setEntityState(this, (byte) 19);
            }
        }

        if (this.inWater) {
            if (this.squidRotation < (float) Math.PI) {
                float f = this.squidRotation / (float) Math.PI;
                this.tentacleAngle = MathHelper.sin(f * f * (float) Math.PI) * (float) Math.PI * 0.25F;

                if ((double) f > 0.75D) {
                    this.randomMotionSpeed = 1.0F;
                    this.rotateSpeed = 1.0F;
                } else {
                    this.rotateSpeed *= 0.8F;
                }
            } else {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.rotateSpeed *= 0.99F;
            }

            if (!this.world.isRemote) {
                this.motionX = (double) (this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double) (this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double) (this.randomMotionVecZ * this.randomMotionSpeed);
            }

            float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float) MathHelper.atan2(this.motionX, this.motionZ)) * (180F / (float) Math.PI) - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.squidYaw = (float) ((double) this.squidYaw + Math.PI * (double) this.rotateSpeed * 1.5D);
            this.squidPitch += (-((float) MathHelper.atan2((double) f1, this.motionY)) * (180F / (float) Math.PI) - this.squidPitch) * 0.1F;
        } else {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float) Math.PI * 0.25F;

            if (!this.world.isRemote) {
                this.motionX = 0.0D;
                this.motionZ = 0.0D;

                if (this.isPotionActive(MobEffects.LEVITATION)) {
                    this.motionY += 0.05D * (double) (this.getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - this.motionY;
                } else if (!this.hasNoGravity()) {
                    this.motionY -= 0.08D;
                }

                this.motionY *= 0.9800000190734863D;
            }

            this.squidPitch = (float) ((double) this.squidPitch + (double) (-90.0F - this.squidPitch) * 0.02D);
        }
    }

    public void moveEntityWithHeading(float strafe, float forward) {
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
    }

    public boolean getCanSpawnHere() {
        return this.posY > 45.0D && this.posY < (double) this.world.getSeaLevel() && super.getCanSpawnHere();
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 19) {
            this.squidRotation = 0.0F;
        } else {
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
        private final EntityColoredSquid squid;

        public AIMoveRandom(EntityColoredSquid squid) {
            this.squid = squid;
        }

        public boolean shouldExecute() {
            return true;
        }

        public void updateTask() {
            int i = this.squid.getIdleTime();

            if (i > 100) {
                this.squid.setMovementVector(0.0F, 0.0F, 0.0F);
            } else if (this.squid.getRNG().nextInt(50) == 0 || !this.squid.inWater || !this.squid.hasMovementVector()) {
                float f = this.squid.getRNG().nextFloat() * ((float) Math.PI * 2F);
                float f1 = MathHelper.cos(f) * 0.2F;
                float f2 = -0.1F + this.squid.getRNG().nextFloat() * 0.2F;
                float f3 = MathHelper.sin(f) * 0.2F;
                this.squid.setMovementVector(f1, f2, f3);
            }
        }
    }
}
