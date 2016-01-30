package com.fabbe50.compressedblocks.item;

import com.fabbe50.compressedblocks.reference.SubTypeRef;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 27/01/2016.
 */
public class ItemPotatoBlock extends ItemBlock {
    static String[] subBlocks = SubTypeRef.potato;

    public ItemPotatoBlock(Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    public String getUnlocalizedName (ItemStack itemStack) {
        int i = itemStack.getItemDamage();
        if (i < 0 || i > subBlocks.length) {
            i = 0;
        }

        return super.getUnlocalizedName() + "." + subBlocks[i];
    }

    public int getMetadata (int meta) {
        return meta;
    }
}
