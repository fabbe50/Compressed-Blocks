package com.fabbe50.compressedblocks.core.event;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 01/07/2017.
 */
public class GrassEatEvent {
    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public void sheepEatGrassEvent(LivingEvent event) {
        if (!event.getEntity().world.isRemote) {
            if (event.getEntity() instanceof EntitySheep) {
                if (event.getEntity().world.getBlockState(event.getEntity().getPosition().down()).getBlock() == BlockRegistry.COMPRESSED_GRASS && ((EntitySheep) event.getEntity()).getSheared()) {
                    ((EntitySheep) event.getEntity()).eatGrassBonus();
                    event.getEntity().world.setBlockState(event.getEntity().getPosition().down(), BlockRegistry.COMPRESSED_GRASS_EATEN.getStateFromMeta(BlockRegistry.COMPRESSED_GRASS.getMetaFromState(event.getEntity().world.getBlockState(event.getEntity().getPosition().down()))));
                }
            }
        }
    }
}
