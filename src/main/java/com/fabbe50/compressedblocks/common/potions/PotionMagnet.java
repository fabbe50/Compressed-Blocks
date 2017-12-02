package com.fabbe50.compressedblocks.common.potions;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.registry.PotionRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 01/12/2017 - 11:01 PM.
 */
public class PotionMagnet extends PotionCustom {
    private static int magnet_range = 5;
    private static List<EntityItem> noGravItems = new ArrayList<>();

    public PotionMagnet(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return this == PotionRegistry.POTION_MAGNET;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int isActive) {
        if (this == PotionRegistry.POTION_MAGNET) {
            if (entity instanceof EntityPlayer && !entity.isSneaking()) {
                List<EntityItem> items = entity.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(entity.getPosition().add(-magnet_range, -magnet_range, -magnet_range), entity.getPosition().add(magnet_range, magnet_range, magnet_range)));
                for (EntityItem item : items) {
                    lookAt(((EntityPlayer) entity).posX, ((EntityPlayer) entity).posY, ((EntityPlayer) entity).posZ, item);
                    double factor = 0.02d;
                    item.setNoGravity(true);
                    noGravItems.add(item);
                    item.motionX += (entity.posX - item.posX) * factor;
                    item.motionY += (entity.posY - item.posY) * factor;
                    item.motionZ += (entity.posZ - item.posZ) * factor;
                }
                for (EntityItem item : noGravItems) {
                    if (item.getDistanceToEntity(entity) >= 6) {
                        item.setNoGravity(false);
                        noGravItems.remove(item);
                    }
                }
            }
        }
    }

    @Override
    public boolean hasStatusIcon() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/icons/magnet.png"));
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/icons/magnet.png"));
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }

    public static void killEffects(EntityLivingBase entity) {
        for (EntityItem item : noGravItems) {
            if (item.hasNoGravity()) {
                item.setNoGravity(false);
                noGravItems.remove(item);
            }
        }
    }
}
