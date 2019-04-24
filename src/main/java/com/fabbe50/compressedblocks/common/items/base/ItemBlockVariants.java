package com.fabbe50.compressedblocks.common.items.base;

import com.fabbe50.compressedblocks.core.interfaces.IMetaName;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe on 03/12/2017 - 2:22 AM.
 */
public class ItemBlockVariants extends ItemBlock {
    public ItemBlockVariants(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return super.getTranslationKey() + "_" + ((IMetaName)this.block).getSpecialName(stack);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }
}
