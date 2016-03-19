package com.fabbe50.compressedblocks.creativetab;

import com.fabbe50.compressedblocks.init.ModBlocks;
import com.fabbe50.compressedblocks.init.ModItems;
import com.fabbe50.compressedblocks.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class CreativeTabCB {
    public static CreativeTabs CB_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase() + ".items") {
        @Override
        public Item getTabIconItem() {
            return ModItems.endgamium;
        }
    };
    public static CreativeTabs CB_TAB2 = new CreativeTabs(Reference.MOD_ID.toLowerCase() + ".blocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(ModBlocks.comprpotatoblock);
        }
    };
}
