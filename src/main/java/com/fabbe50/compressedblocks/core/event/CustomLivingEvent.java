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
            if (event.getEntityLiving().getActivePotionEffect(PotionRegistry.POTION_FLIGHT) != null) {
                if (!event.getEntityLiving().getTags().contains("potionFlight"))
                    event.getEntityLiving().addTag("potionFlight");
            } else if (event.getEntityLiving().getActivePotionEffect(PotionRegistry.POTION_FLIGHT) == null) {
                if (event.getEntityLiving().getTags().contains("potionFlight")) {
                    event.getEntityLiving().removeTag("potionFlight");
                    PotionFlight.killEffects(event.getEntityLiving());
                }
            }

            if (event.getEntityLiving().getActivePotionEffect(PotionRegistry.POTION_MAGNET) != null) {
                if (!event.getEntityLiving().getTags().contains("potionMagnet"))
                    event.getEntityLiving().addTag("potionMagnet");
            } else if (event.getEntityLiving().getActivePotionEffect(PotionRegistry.POTION_MAGNET) == null) {
                if (event.getEntityLiving().getTags().contains("potionMagnet")) {
                    event.getEntityLiving().removeTag("potionMagnet");
                    PotionMagnet.killEffects(event.getEntityLiving());
                }
            }
        }
    }
}
