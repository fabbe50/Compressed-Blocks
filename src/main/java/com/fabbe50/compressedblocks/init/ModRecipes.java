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

    @GameRegistry.ObjectHolder("ExtraUtilities:cobblestone_compressed:7")
    public static final Item compr7cobble = null;
    @GameRegistry.ObjectHolder("AWWayofTime:blockCrystal")
    public static final Item crystalCluster = null;
    @GameRegistry.ObjectHolder("ExtraUtilities:decorativeBlock1")
    public static final Item unstableBlock = null;
    @GameRegistry.ObjectHolder("ThaumicTinkerer:kamiResource:2")
    public static final Item ichorium = null;
    @GameRegistry.ObjectHolder("ForbiddenMagic:StarBlock")
    public static final Item starBlock = null;
    @GameRegistry.ObjectHolder("Botania:manaResource:14")
    public static final Item terraSteel = null;
    @GameRegistry.ObjectHolder("witchery:ingredient:96")
    public static final Item brewOfFlowingSpirit = null;
    @GameRegistry.ObjectHolder("IC2:itemPartIridium")
    public static final Item iridium = null;
    @GameRegistry.ObjectHolder("ThermalExpansion:Tesseract")
    public static final Item tesseract = null;

    public static void init() {
        if (compr7cobble == null || crystalCluster == null || unstableBlock == null || ichorium == null || starBlock == null || terraSteel == null || brewOfFlowingSpirit == null || iridium == null || tesseract == null) {
            LogHelper.info("GogglesRev is Null");
        }
        else {
            GameRegistry.addShapelessRecipe(new ItemStack(ModItems.endgamium),
                    new ItemStack(compr7cobble), new ItemStack(crystalCluster), new ItemStack(unstableBlock),
                    new ItemStack(ichorium), new ItemStack(starBlock), new ItemStack(terraSteel),
                    new ItemStack(brewOfFlowingSpirit), new ItemStack(iridium), new ItemStack(tesseract));
        }

    }
}
