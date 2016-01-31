package com.fabbe50.compressedblocks.item;

import com.fabbe50.compressedblocks.lib.EnumPotato;
import com.fabbe50.compressedblocks.reference.SubTypeRef;
import com.fabbe50.compressedblocks.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 27/01/2016.
 */
public class ItemPotatoBlock extends ItemBlock {
    public ItemPotatoBlock(Block block) {
        super(block);
        this.setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName (ItemStack itemStack) {
        EnumPotato potato = EnumPotato.values()[itemStack.getItemDamage()];
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + "_" + potato.getName().toLowerCase());
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
