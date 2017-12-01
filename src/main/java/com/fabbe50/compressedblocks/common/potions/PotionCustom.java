package com.fabbe50.compressedblocks.common.potions;

import com.fabbe50.compressedblocks.core.registry.PotionRegistry;
import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.List;

/**
 * Created by fabbe on 28/11/2017 - 9:12 PM.
 */
public class PotionCustom extends Potion {
    private int magnet_range = 5;

    public PotionCustom(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (this == PotionRegistry.POTION_MAGNET)
            return true;
        else
            return false;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int p_76394_2_) {
        if (this == PotionRegistry.POTION_MAGNET) {
            if (entity instanceof EntityPlayer && !entity.isSneaking()) {
                List<EntityItem> items = entity.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(entity.getPosition().add(-magnet_range, -magnet_range, -magnet_range), entity.getPosition().add(magnet_range, magnet_range, magnet_range)));
                for (EntityItem item : items) {
                    lookAt(((EntityPlayer) entity).posX, ((EntityPlayer) entity).posY, ((EntityPlayer) entity).posZ, item);
                    double factor = 0.02d;
                    item.setNoGravity(true);
                    item.motionX += (entity.posX - item.posX) * factor;
                    item.motionY += (entity.posY - item.posY) * factor;
                    item.motionZ += (entity.posZ - item.posZ) * factor;
                }
                List<EntityItem> itemsOutOfBounds = entity.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(entity.getPosition().add(-magnet_range - 3, -magnet_range - 3, -magnet_range - 3), entity.getPosition().add(magnet_range + 3, magnet_range + 3, magnet_range + 3)));
                for (EntityItem item : itemsOutOfBounds) {
                    if (item.getDistanceToEntity(entity) >= 6)
                        item.setNoGravity(false);
                }
            }
        }
    }

    public static void lookAt(double px, double py, double pz , EntityItem me) {
        double dirx = me.getPosition().getX() - px;
        double diry = me.getPosition().getY() - py + 1;
        double dirz = me.getPosition().getZ() - pz;

        double len = Math.sqrt(dirx*dirx + diry*diry + dirz*dirz);

        dirx /= len;
        diry /= len;
        dirz /= len;

        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);

        //to degree
        pitch = pitch * 180.0 / Math.PI;
        yaw = yaw * 180.0 / Math.PI;

        yaw += 90f;
        me.rotationPitch = (float)pitch;
        me.rotationYaw = (float)yaw;
    }
}
