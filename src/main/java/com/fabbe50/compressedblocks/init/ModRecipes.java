package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class ModRecipes {

    @GameRegistry.ObjectHolder("thaumcraft:")
    public static final Item ichorium = null;

    public static void init() {
        if (ichorium == null) {
            LogHelper.info("Ichorium is Null");
        }
        else {
            GameRegistry.addRecipe(new ItemStack(ModItems.endgamium), "qdd", "ddd", "ddd", 'q', new ItemStack(ichorium), 'd', new ItemStack(Items.stick));
        }

    }
}
