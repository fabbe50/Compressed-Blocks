package com.fabbe50.compressedblocks.common.potions;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.registry.PotionRegistry;
import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 01/12/2017 - 10:58 PM.
 */
public class PotionFlight extends PotionCustom {

    public PotionFlight(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if (this == PotionRegistry.POTION_FLIGHT) {
            if (entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).capabilities.allowFlying = true;
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
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/icons/flight.png"));
        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/icons/flight.png"));
        Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
    }

    public static void killEffects(EntityLivingBase entity) {
        ((EntityPlayer) entity).capabilities.allowFlying = false;
        ((EntityPlayer) entity).capabilities.isFlying = false;
    }
}
