package com.fabbe50.compressedblocks.item.items;

import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 19/03/2016.
 */
public class ItemMissing extends ItemCB {
    public ItemMissing () {
        super();
        this.setUnlocalizedName("missing");
        this.setCreativeTab(null);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) { //Tooltip text adder
        list.add(ColorHelper.magenta + "If you see this, the recipe didn't instantiate properly");
        list.add(ColorHelper.magenta + "To fix this, please install missing mods or enable fallback recipes.");
    }
}
