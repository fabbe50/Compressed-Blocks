package com.fabbe50.compressedblocks.item.items;

import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class ItemDummy extends ItemCB {
    int totalDamage = 696969;

    public ItemDummy() {
        super();
        this.setUnlocalizedName("dummyitem");
        this.setCreativeTab(null);
    }

    @Override
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par) { //Tooltip text adder
        list.add(ColorHelper.dark_gray + "Creative Item");
        list.add("");
        list.add(ColorHelper.lime + "This is a very dumb item!");
        list.add("");
        list.add(ColorHelper.magenta + "This item is so dumb, that even an imbecile seems smart.");
        list.add(ColorHelper.magenta + "And also... potatoes...");
        list.add(ColorHelper.magenta + "Shhhh... I know I'm not funny... this is not supposed to be funny even...");
        list.add(ColorHelper.magenta + "Just get over it.");
    }

    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.totalDamage, 0));
        return multimap;
    }
}
