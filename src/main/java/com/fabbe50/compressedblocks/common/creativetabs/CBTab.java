package com.fabbe50.compressedblocks.common.creativetabs;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe on 30/05/2017.
 */
public class CBTab {
    public static CreativeTabs compressedBlocksTab = new CreativeTabs(Reference.MOD_ID.toLowerCase() + ".compressedblocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(BlockRegistry.ENDGAMIUMBLOCKC);
        }
    };
    public static CreativeTabs blockTab = new CreativeTabs(Reference.MOD_ID.toLowerCase() + ".blocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(BlockRegistry.POTATO_BLOCK);
        }
    };
    public static CreativeTabs itemTab = new CreativeTabs(Reference.MOD_ID.toLowerCase() + ".items") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.STICK);
        }
    };
}
