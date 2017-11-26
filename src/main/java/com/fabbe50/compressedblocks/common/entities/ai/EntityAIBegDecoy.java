package com.fabbe50.compressedblocks.common.entities.ai;

import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 23/09/2016.
 */
public class EntityAIBegDecoy extends EntityAIBase {
    private final EntityCorgi theCorgi;
    private EntityPlayer thePlayer;
    private final World worldObject;
    private final float minPlayerDistance;
    private int timeoutCounter;

    public EntityAIBegDecoy(EntityCorgi corgi, float minDistance) {
        this.theCorgi = corgi;
        this.worldObject = corgi.world;
        this.minPlayerDistance = minDistance;
        this.setMutexBits(2);
    }

    public boolean shouldExecute() {
        this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theCorgi, (double)this.minPlayerDistance);
        return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
    }

    public boolean continueExecuting() {
        return !this.thePlayer.isEntityAlive() ? false : (this.theCorgi.getDistance(this.thePlayer) > (double)(this.minPlayerDistance * this.minPlayerDistance) ? false : this.timeoutCounter > 0 && this.hasPlayerGotBoneInHand(this.thePlayer));
    }

    public void startExecuting() {
        this.theCorgi.setBegging(true);
        this.timeoutCounter = 40 + this.theCorgi.getRNG().nextInt(40);
    }

    public void resetTask() {
        this.theCorgi.setBegging(false);
        this.thePlayer = null;
    }

    public void updateTask() {
        this.theCorgi.getLookHelper().setLookPosition(this.thePlayer.posX, this.thePlayer.posY + (double)this.thePlayer.getEyeHeight(), this.thePlayer.posZ, 10.0F, (float)this.theCorgi.getVerticalFaceSpeed());
        --this.timeoutCounter;
    }
    
    private boolean hasPlayerGotBoneInHand(EntityPlayer player) {
        for (EnumHand enumhand : EnumHand.values()) {
            ItemStack itemstack = player.getHeldItem(enumhand);

            if (itemstack != null) {
                if (this.theCorgi.isTamed() && itemstack.getItem() == Items.BONE) {
                    return true;
                }

                if (this.theCorgi.isBreedingItem(itemstack)) {
                    return true;
                }
            }
        }

        return false;
    }
}
