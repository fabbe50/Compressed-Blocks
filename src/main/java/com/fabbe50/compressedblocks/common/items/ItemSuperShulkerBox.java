package com.fabbe50.compressedblocks.common.items;

import com.thefifthidiot.tficore.utility.helper.ChatHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 19/02/2017.
 */
public class ItemSuperShulkerBox extends ItemBlock {
    public ItemSuperShulkerBox(Block block) {
        super(block);
        this.setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(ChatHelper.MAGENTA + "EXPERIMENTAL");
        tooltip.add(ChatHelper.MAGENTA + "They function, just having some issues with the item textures.");
    }
}
