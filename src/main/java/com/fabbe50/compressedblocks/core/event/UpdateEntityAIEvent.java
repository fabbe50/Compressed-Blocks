package com.fabbe50.compressedblocks.core.event;

import com.fabbe50.compressedblocks.common.entities.ai.EntityAIEatCompressedGrass;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 25/11/2017 - 9:36 PM.
 */
public class UpdateEntityAIEvent {
    @SubscribeEvent
    public void onWorldJoin(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote) {
            if (event.getEntity() instanceof EntitySheep) {
                ((EntitySheep)event.getEntity()).tasks.addTask(5, new EntityAIEatCompressedGrass((EntityLiving)event.getEntity()));
            }
        }
    }
}
