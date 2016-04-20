package com.fabbe50.compressedblocks.common.item.itemblocks;

import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 19/03/2016.
 */
public class ItemEndgamiumCBlock extends ItemBlock {
    public ItemEndgamiumCBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) {
        list.add(ColorHelper.magenta + "Your patience, sure does amaze me!");
        list.add(ColorHelper.blue + "This block contains: ");
        list.add(ColorHelper.blue + "3,486,784,401 pieces of cobblestone");
        list.add(ColorHelper.blue + "3,486,784,401 potato blocks or 31,381,059,609 individual potatoes");
    }
}
