package com.fabbe50.compressedblocks.core.event;

import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 14/11/2017 - 3:53 PM.
 */
public class TrinketEnderEvent {
    @SubscribeEvent
    public void preventDimensionTeleport(EntityTravelToDimensionEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            //if ((((EntityPlayer)event.getEntity()).inventory.hasItemStack(new ItemStack(ItemRegistry.TRINKET, 1, 2)))) {
            //    event.setCanceled(true);
            //}
        }
    }

    @SubscribeEvent
    public void preventEnderTeleport(EnderTeleportEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            //if ((((EntityPlayer)event.getEntity()).inventory.hasItemStack(new ItemStack(ItemRegistry.TRINKET, 1, 1)))) {
            //    event.setCanceled(true);
            //}
        }
    }
}
