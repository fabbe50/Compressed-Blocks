package com.fabbe50.compressedblocks.common.item.items;

import com.fabbe50.compressedblocks.common.item.ItemCB;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 14/01/2016.
 */
public class ItemEndgamium extends ItemCB {
    public ItemEndgamium() {
        super();
        this.setUnlocalizedName("endgamium");
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) {
        list.add(ColorHelper.magenta + "You tried so hard, and got so far.");
        list.add(ColorHelper.magenta + "But in the end you needed at least two ingots to do shit...");
    }
}
