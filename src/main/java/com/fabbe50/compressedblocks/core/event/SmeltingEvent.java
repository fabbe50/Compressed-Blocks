package com.fabbe50.compressedblocks.core.event;

import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * Created by fabbe on 18/06/2017.
 */
public class SmeltingEvent {
    @SubscribeEvent
    public void smelting(PlayerEvent.ItemSmeltedEvent event) {
        if (!event.player.world.isRemote) {
            if (event.smelting.getItem() == ItemRegistry.BEDROCK_INGOT) {
                event.player.addExperienceLevel(100);
            }
        }
    }
}
