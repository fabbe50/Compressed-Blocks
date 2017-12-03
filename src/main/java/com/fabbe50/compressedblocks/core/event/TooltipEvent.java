package com.fabbe50.compressedblocks.core.event;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.utils.helper.ChatHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 03/12/2017 - 5:10 PM.
 */
public class TooltipEvent {
    private static List<Block> blocks = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof ItemBlock) {
            if (blocks.contains(Block.getBlockFromItem(event.getItemStack().getItem()))) {
                event.getToolTip().add(1, ChatHelper.MAGENTA + "[EXPERIMENTAL]");
            }
        }
    }

    public static void init() {
        blocks.add(BlockRegistry.NUKE);
    }
}
