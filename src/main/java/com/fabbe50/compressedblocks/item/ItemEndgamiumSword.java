package com.fabbe50.compressedblocks.item;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemSword;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class ItemEndgamiumSword extends ItemCB{
    float atkDmg;
    //private final ItemCB.ToolMaterial toolMats;

    public ItemEndgamiumSword() {
        super();
        this.setUnlocalizedName("endgamiumsword");
        this.maxStackSize = 1;
        this.setMaxDamage(9001);
        //this.atkDmg = 4.0f + toolMats.getDamageVsEntity();
    }
}
