package com.fabbe50.compressedblocks.common.items.base;

import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.lib.EnumCompressed;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by fabbe50 on 24/09/2016.
 */
public class CompressedItemBase extends ItemBase {
    public CompressedItemBase(String itemName, CreativeTabs tab) {
        super(itemName, tab);
        this.setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + "_" + EnumCompressed.byMetadata(stack.getMetadata()).getName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        final List<ItemStack> items = Stream.of(EnumCompressed.values()).map(enumType -> new ItemStack(this, 1, enumType.getMetadata())).collect(Collectors.toList());
        subItems.addAll(items);
    }
}
