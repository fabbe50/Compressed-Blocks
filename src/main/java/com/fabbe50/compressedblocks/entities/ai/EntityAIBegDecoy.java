package com.fabbe50.compressedblocks.entities.ai;

import com.fabbe50.compressedblocks.entities.tamables.EntityCorgi;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 24/02/2016.
 */
public class EntityAIBegDecoy extends EntityAIBase {
    private EntityCorgi theCorgi;
    private EntityPlayer thePlayer;
    private World worldObject;
    private float minPlayerDistance;
    private int field_75384_e;
    private static final String __OBFID = "CL_00001576";

    public EntityAIBegDecoy(EntityCorgi p_i1617_1_, float p_i1617_2_) {
        this.theCorgi = p_i1617_1_;
        this.worldObject = p_i1617_1_.worldObj;
        this.minPlayerDistance = p_i1617_2_;
        this.setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theCorgi, (double) this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerGotPotatoInHand(this.thePlayer);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return !this.thePlayer.isEntityAlive() ? false : (this.theCorgi.getDistanceSqToEntity(this.thePlayer) > (double) (this.minPlayerDistance * this.minPlayerDistance) ? false : this.field_75384_e > 0 && this.hasPlayerGotPotatoInHand(this.thePlayer));
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.theCorgi.func_70918_i(true);
        this.field_75384_e = 40 + this.theCorgi.getRNG().nextInt(40);
    }

    /**
     * Resets the task
     */
    public void resetTask() {
        this.theCorgi.func_70918_i(false);
        this.thePlayer = null;
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        this.theCorgi.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + (double) this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, (float) this.theCorgi.getVerticalFaceSpeed());
        --this.field_75384_e;
    }

    /**
     * Gets if the Player has the Bone in the hand.
     */
    private boolean hasPlayerGotPotatoInHand(EntityPlayer p_75382_1_) {
        ItemStack itemstack = p_75382_1_.inventory.getCurrentItem();
        return itemstack == null ? false : (!this.theCorgi.isTamed() && itemstack.getItem() == Items.baked_potato ? true : this.theCorgi.isBreedingItem(itemstack));
    }
}

