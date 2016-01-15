package com.fabbe50.compressedblocks.creativetab;

import com.fabbe50.compressedblocks.init.ModItems;
import com.fabbe50.compressedblocks.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class CreativeTabCB {
    public static final CreativeTabs CB_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

        @Override
        public Item getTabIconItem() {
            return ModItems.endgamium;
        }
    };
}
