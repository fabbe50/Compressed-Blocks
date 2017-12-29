package com.fabbe50.compressedblocks.core.event;

import com.fabbe50.compressedblocks.common.potions.PotionFlight;
import com.fabbe50.compressedblocks.common.potions.PotionMagnet;
import com.fabbe50.compressedblocks.core.registry.PotionRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 01/12/2017 - 9:45 PM.
 */
public class CustomLivingEvent {
    @SubscribeEvent
    public void endPotionEvent(LivingEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            if (event.getEntityLiving().getActivePotionEffect(PotionRegistry.POTION_FLIGHT) == null) {
                if (!((EntityPlayer) event.getEntityLiving()).capabilities.isCreativeMode) {
                    try {
                        PotionFlight.killEffects(event.getEntityLiving());
                    } catch (Exception e) {

                    }
                }
            }
            if (event.getEntityLiving().getActivePotionEffect(PotionRegistry.POTION_MAGNET) == null) {
                try {
                    PotionMagnet.killEffects(event.getEntityLiving());
                } catch (Exception e) {

                }
            }
        }
    }
}
