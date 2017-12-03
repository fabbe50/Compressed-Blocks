package com.fabbe50.compressedblocks.common.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.potion.Potion;

/**
 * Created by fabbe on 28/11/2017 - 9:12 PM.
 */
public class PotionCustom extends Potion {
    public PotionCustom(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return false;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int isActive) {
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
