package com.fabbe50.compressedblocks.core.event;

import com.fabbe50.compressedblocks.core.handler.AchievementHandler;
import com.fabbe50.compressedblocks.init.ModBlocks;
import com.fabbe50.compressedblocks.init.ModItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 07/03/2016.
 */
public class RegisterCraftingEvent {

    @SubscribeEvent
    public void crafting(PlayerEvent.ItemCraftedEvent event) {
        if(event.crafting.getItem().equals(ItemBlock.getItemFromBlock(ModBlocks.comprstarblock))) {
            event.player.addStat(AchievementHandler.compressedStarblock, 1);
        }
        if(event.crafting.getItem().equals(ModItems.endgamium)) {
            event.player.addStat(AchievementHandler.acquireEndgamium, 1);
        }
        if(event.crafting.getItem().equals(new ItemStack(ModBlocks.comprpotatoblock,1,0).getItem())) {
            event.player.addStat(AchievementHandler.potatoesfordays, 1);
        }
        if(event.crafting.getItem().equals(new ItemStack(ModBlocks.comprpotatoblock,1,7).getItem())) {
            event.player.addStat(AchievementHandler.foodIsWasted, 1);
        }
        if(event.crafting.getItem().equals(new ItemStack(ModItems.endgamiummultitool).getItem())) {
            event.player.addStat(AchievementHandler.endgameMining, 1);
        }
        if(event.crafting.getItem().equals(new ItemStack(ModItems.endgamiumsword).getItem())) {
            event.player.addStat(AchievementHandler.endgameSword, 1);
        }
    }
}
