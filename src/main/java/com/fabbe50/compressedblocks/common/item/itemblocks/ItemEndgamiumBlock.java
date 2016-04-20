package com.fabbe50.compressedblocks.common.item.itemblocks;

import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 14/03/2016.
 */
public class ItemEndgamiumBlock extends ItemBlock {
    public ItemEndgamiumBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) {
        list.add(ColorHelper.magenta + "Congratulations, you have no life!");
        list.add(ColorHelper.blue + "This block contains: ");
        list.add(ColorHelper.blue + "387,420,489 pieces of cobblestone");
        list.add(ColorHelper.blue + "387,420,489 potato blocks or 3,486,784,401 individual potatoes");
    }
}
