package com.fabbe50.compressedblocks.common.entity.tamables.corgis;

import com.fabbe50.compressedblocks.common.entity.ai.EntityAIBegDecoy;
import com.fabbe50.compressedblocks.common.item.ItemCorgiFood;
import com.fabbe50.compressedblocks.core.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 10/02/2016.
 */
public class EntityCorgi extends EntityTameable {
    private float field_70926_e;
    private float field_70924_f;
    /** true is the corgi is wet else false */
    private boolean isShaking;
    private boolean field_70928_h;
    /** This time increases while corgi is shaking and emitting water particles. */
    private float timeCorgiIsShaking;
    private float prevTimeCorgiIsShaking;
    public int safeCorgiAmount = Textures.safeCorgiAmount;
    public boolean canBeTamed;
    public int amountOfCorgis;

    public EntityCorgi(World p_i1696_1_) {
        super(p_i1696_1_);
        this.setSize(0.6F, 0.8F);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        //this.tasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityPig.class, 200, false));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(9, new EntityAIBegDecoy(this, 8.0F));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
        //this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPig.class, 200, false));
        this.setTamed(false);
        //this.amountOfCorgis = GetFilesFromDir.getFileAmount(Textures.corgiDir, safeCorgiAmount);
        this.setCorgiType(rand.nextInt(Textures.corgiAmount - 1));
        this.canBeTamed = true;
        applyTraits();
    }

    public void applyTraits() {
        switch (getCorgiType()) {
            case 5:

            case 8:
                this.setCollarColor(BlockColored.func_150032_b(13));
                break;
            case 9:
                this.setCollarColor(BlockColored.func_150032_b(11));
                break;
            default:
                this.setCollarColor(BlockColored.func_150032_b(1));
        }
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);

        if (this.isTamed())
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        }
    }

    public boolean isAIEnabled()
    {
        return true;
    }

    public void setAttackTarget(EntityLivingBase p_70624_1_) {
        super.setAttackTarget(p_70624_1_);

        if (p_70624_1_ == null)
        {
            this.setAngry(false);
        }
        else if (!this.isTamed())
        {
            this.setAngry(true);
        }
    }

    protected void updateAITick()
    {
        this.dataWatcher.updateObject(18, Float.valueOf(this.getHealth()));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, new Float(this.getHealth()));
        this.dataWatcher.addObject(19, new Byte((byte)0));
        this.dataWatcher.addObject(20, new Byte((byte)BlockColored.func_150032_b(1)));
        this.dataWatcher.addObject(22, 0);
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.wolf.step", 0.15F, 1.0F);
    }

    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setBoolean("Angry", this.isAngry());
        tag.setByte("CollarColor", (byte)this.getCollarColor());
        tag.setInteger("CorgiType", (int)this.getCorgiType());
    }

    public int getCorgiType() {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        this.setAngry(p_70037_1_.getBoolean("Angry"));
        this.setCorgiType(p_70037_1_.getInteger("CorgiType"));

        if (p_70037_1_.hasKey("CollarColor", 99))
        {
            this.setCollarColor(p_70037_1_.getByte("CollarColor"));
        }
    }

    public void setCorgiType(int i) {
        if (!worldObj.isRemote) {
            this.dataWatcher.updateObject(22, Integer.valueOf(i));
        }
    }

    protected String getLivingSound() {
        return this.isAngry() ? "mob.wolf.growl" : (this.rand.nextInt(3) == 0 ? (this.isTamed() && this.dataWatcher.getWatchableObjectFloat(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    protected Item getDropItem()
    {
        return Item.getItemById(-1);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h && !this.hasPath() && this.onGround)
        {
            this.field_70928_h = true;
            this.timeCorgiIsShaking = 0.0F;
            this.prevTimeCorgiIsShaking = 0.0F;
            this.worldObj.setEntityState(this, (byte)8);
        }
    }

    public void onUpdate() {
        super.onUpdate();
        this.field_70924_f = this.field_70926_e;

        if (this.func_70922_bv())
        {
            this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
        }
        else
        {
            this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
        }

        if (this.func_70922_bv())
        {
            this.numTicksToChaseTarget = 10;
        }

        if (this.isWet())
        {
            this.isShaking = true;
            this.field_70928_h = false;
            this.timeCorgiIsShaking = 0.0F;
            this.prevTimeCorgiIsShaking = 0.0F;
        }
        else if ((this.isShaking || this.field_70928_h) && this.field_70928_h)
        {
            if (this.timeCorgiIsShaking == 0.0F)
            {
                this.playSound("mob.wolf.shake", this.getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }

            this.prevTimeCorgiIsShaking = this.timeCorgiIsShaking;
            this.timeCorgiIsShaking += 0.05F;

            if (this.prevTimeCorgiIsShaking >= 2.0F)
            {
                this.isShaking = false;
                this.field_70928_h = false;
                this.prevTimeCorgiIsShaking = 0.0F;
                this.timeCorgiIsShaking = 0.0F;
            }

            if (this.timeCorgiIsShaking > 0.4F)
            {
                float f = (float)this.boundingBox.minY;
                int i = (int)(MathHelper.sin((this.timeCorgiIsShaking - 0.4F) * (float)Math.PI) * 7.0F);

                for (int j = 0; j < i; ++j)
                {
                    float f1 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    float f2 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
                    this.worldObj.spawnParticle("splash", this.posX + (double)f1, (double)(f + 0.8F), this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ);
                }
            }
        }

        if (!isTamed()) {
            if (!this.worldObj.isRemote) {
                spawnTamed(worldObj.getClosestPlayerToEntity(getEntityCorgi(), 8.0d));
            }
        }
    }

    public EntityCorgi getEntityCorgi () {
        return EntityCorgi.this;
    }

    public void spawnTamed(EntityPlayer player) {
        if (canBeTamed && worldObj.getClosestPlayerToEntity(getEntityCorgi(), 8.0d) != null) {
            this.setTamed(true);
            this.setPathToEntity((PathEntity) null);
            this.setAttackTarget((EntityLivingBase) null);
            this.aiSit.setSitting(true);
            this.setHealth(20.0F);
            this.func_152115_b(player.getUniqueID().toString());
            this.playTameEffect(true);
            this.worldObj.setEntityState(this, (byte) 7);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean getCorgiShaking()
    {
        return this.isShaking;
    }

    @SideOnly(Side.CLIENT)
    public float getShadingWhileShaking(float p_70915_1_) {
        return 0.75F + (this.prevTimeCorgiIsShaking + (this.timeCorgiIsShaking - this.prevTimeCorgiIsShaking) * p_70915_1_) / 2.0F * 0.25F;
    }

    @SideOnly(Side.CLIENT)
    public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
        float f2 = (this.prevTimeCorgiIsShaking + (this.timeCorgiIsShaking - this.prevTimeCorgiIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        else if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        return MathHelper.sin(f2 * (float)Math.PI) * MathHelper.sin(f2 * (float)Math.PI * 11.0F) * 0.15F * (float)Math.PI;
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    @SideOnly(Side.CLIENT)
    public float getInterestedAngle(float p_70917_1_) {
        return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * p_70917_1_) * 0.15F * (float)Math.PI;
    }

    public int getVerticalFaceSpeed()
    {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            Entity entity = p_70097_1_.getEntity();
            this.aiSit.setSitting(false);

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                p_70097_2_ = (p_70097_2_ + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(p_70097_1_, p_70097_2_);
        }
    }

    public boolean attackEntityAsMob(Entity p_70652_1_) {
        int i = this.isTamed() ? 4 : 2;
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage(this), (float)i);
    }

    public void setTamed(boolean p_70903_1_) {
        super.setTamed(p_70903_1_);

        if (p_70903_1_)
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        }
    }

    public boolean interact(EntityPlayer p_70085_1_) {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();

        if (this.isTamed()) {
            if (itemstack != null) {
                if (itemstack.getItem() instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) itemstack.getItem();

                    if (ItemCorgiFood.isCorgisFavoriteFood(itemfood) && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F) {
                        if (!p_70085_1_.capabilities.isCreativeMode) {
                            --itemstack.stackSize;
                        }

                        this.heal((float) itemfood.func_150905_g(itemstack));

                        if (itemstack.stackSize <= 0) {
                            p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack) null);
                        }

                        return true;
                    }
                } else if (itemstack.getItem() == Items.dye) {
                    if (getCorgiType() != 8 && getCorgiType() != 9) {
                        int i = BlockColored.func_150032_b(itemstack.getItemDamage());

                        setCollarColorOnColoring(itemstack, p_70085_1_, i);
                    }
                }
            }

            if (this.func_152114_e(p_70085_1_) && !this.worldObj.isRemote && !this.isBreedingItem(itemstack)) {
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity((PathEntity) null);
                this.setTarget((Entity) null);
                this.setAttackTarget((EntityLivingBase) null);
            }
        } else if (itemstack != null && itemstack.getItem() == Items.baked_potato && !this.isAngry()) {
            if (!p_70085_1_.capabilities.isCreativeMode) {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0) {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack) null);
            }

            if (!this.worldObj.isRemote) {
                if (this.rand.nextInt(3) == 0) {
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity) null);
                    this.setAttackTarget((EntityLivingBase) null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.func_152115_b(p_70085_1_.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte) 7);
                } else {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte) 6);
                }
            }

            return true;
        }

        return super.interact(p_70085_1_);
    }

    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(byte p_70103_1_) {
        if (p_70103_1_ == 8) {
            this.field_70928_h = true;
            this.timeCorgiIsShaking = 0.0F;
            this.prevTimeCorgiIsShaking = 0.0F;
        } else {
            super.handleHealthUpdate(p_70103_1_);
        }
    }

    @SideOnly(Side.CLIENT)
    public float getTailRotation() {
        return this.isAngry() ? 1.5393804F : (this.isTamed() ? (0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * (float)Math.PI : ((float)Math.PI / 5F));
    }

    public boolean isBreedingItem(ItemStack p_70877_1_) {
        return p_70877_1_ == null ? false : (!(p_70877_1_.getItem() instanceof ItemFood) ? false : (ItemCorgiFood.isCorgisFavoriteFood(p_70877_1_.getItem())));
    }

    public int getMaxSpawnedInChunk()
    {
        return 8;
    }

    public boolean isAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public void setAngry(boolean p_70916_1_) {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70916_1_)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 | 2)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(b0 & -3)));
        }
    }

    public int getCollarColor()
    {
        return this.dataWatcher.getWatchableObjectByte(20) & 15;
    }

    public void setCollarColor(int p_82185_1_) {
        this.dataWatcher.updateObject(20, Byte.valueOf((byte)(p_82185_1_ & 15)));
    }

    public boolean setCollarColorOnColoring (ItemStack itemstack, EntityPlayer player, int i) {
        if (i != this.getCollarColor())
        {
            this.setCollarColor(i);

            if (!player.capabilities.isCreativeMode && --itemstack.stackSize <= 0)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }

            return true;
        }
        return true;
    }

    public EntityCorgi createChild(EntityAgeable p_90011_1_) {
        EntityCorgi entitycorgi = new EntityCorgi(this.worldObj);
        String s = this.func_152113_b();

        if (s != null && s.trim().length() > 0)
        {
            entitycorgi.func_152115_b(s);
            entitycorgi.setTamed(true);
        }

        return entitycorgi;
    }

    public void func_70918_i(boolean p_70918_1_) {
        if (p_70918_1_)
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
        }
        else
        {
            this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
        }
    }

    public boolean canMateWith(EntityAnimal p_70878_1_) {
        if (p_70878_1_ == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(p_70878_1_ instanceof EntityCorgi))
        {
            return false;
        }
        else
        {
            EntityCorgi entitycorgi = (EntityCorgi)p_70878_1_;
            return !entitycorgi.isTamed() ? false : (entitycorgi.isSitting() ? false : this.isInLove() && entitycorgi.isInLove());
        }
    }

    public boolean func_70922_bv()
    {
        return this.dataWatcher.getWatchableObjectByte(19) == 1;
    }

    protected boolean canDespawn()
    {
        return false; /*!this.isTamed() && this.ticksExisted > 2400;*/
    }

    public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
        if (!(p_142018_1_ instanceof EntityCreeper) && !(p_142018_1_ instanceof EntityGhast))
        {
            if (p_142018_1_ instanceof EntityCorgi)
            {
                EntityCorgi entitycorgi = (EntityCorgi)p_142018_1_;

                if (entitycorgi.isTamed() && entitycorgi.getOwner() == p_142018_2_)
                {
                    return false;
                }
            }

            return p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer)p_142018_2_).canAttackPlayer((EntityPlayer)p_142018_1_) ? false : !(p_142018_1_ instanceof EntityHorse) || !((EntityHorse)p_142018_1_).isTame();
        }
        else
        {
            return false;
        }
    }
}