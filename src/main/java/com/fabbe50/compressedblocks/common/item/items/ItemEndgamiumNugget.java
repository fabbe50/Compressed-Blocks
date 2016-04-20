package com.fabbe50.compressedblocks.common.item.items;

import com.fabbe50.compressedblocks.common.item.ItemCB;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 29/02/2016.
 */
public class ItemEndgamiumNugget extends ItemCB {
    public ItemEndgamiumNugget () {
        super();
        setUnlocalizedName("endgamium_nugget");
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) {
        list.add(ColorHelper.magenta + "Now this is just silly... is this real? Are you for real?");
    }
}
