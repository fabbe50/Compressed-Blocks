package com.fabbe50.compressedblocks.item.items;

import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 29/02/2016.
 */
public class ItemInfusedBone extends ItemCB {
    public ItemInfusedBone () {
        super();
        setUnlocalizedName("infusedbone");
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) {
        list.add(ColorHelper.magenta + "Wha... You... Ehh nevermind me, I just lost half my braincells seeing you");
        list.add(ColorHelper.magenta + "spend all your valuable resources making such a useless thing.");
    }
}
