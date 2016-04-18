package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.reference.ModItemLibrary;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.utility.LogHelper;
import com.fabbe50.compressedblocks.utility.ToolTipHelper;
import com.typesafe.config.Config;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by fabbe50 on 19/03/2016.
 */
public class RecipeDynamic {
    //Mod Items and Blocks
    static ItemStack comprpotato = new ItemStack(ModBlocks.comprpotatoblock, 1, 7);

    //Components
    static ItemStack component1;
    static ItemStack component2;
    static ItemStack component3;
    static ItemStack component4;
    static ItemStack component5;
    static ItemStack component6;
    static ItemStack component7;
    static ItemStack component8;
    static ItemStack component9;

    public static void init() {
        setComponents();
        setRecipes();
    }

    private static void setComponents () {
        if (ModItemLibrary.compr8cobble != null) {
            component1 = new ItemStack(ModItemLibrary.compr8cobble, 1, 7);
            notice(component1, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component1 = new ItemStack(ModFallbackBlocks.comprcobbleblock, 1, 4);
            notice(component1, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component1 = new ItemStack(ModItems.missingitem);
            notice(component1, false);
        }        //Component1: Original: Octuple Compressed Cobblestone [ExtraUtils]; Fallback: Octuple Compressed Cobblestone [CB]
        if (ModItemLibrary.crystalCluster != null) {
            component2 = new ItemStack(ModItemLibrary.crystalCluster);
            notice(component2, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component2 = new ItemStack(Items.skull, 1, 1);
            notice(component2, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component2 = new ItemStack(ModItems.missingitem);
            notice(component2, false);
        }      //Component2: Original: Crystal Cluster [BloodMagic]; Fallback: Wither Skeleton Skull [Minecraft]
        if (ModItemLibrary.unstableBlock != null) {
            component3 = new ItemStack(ModItemLibrary.unstableBlock,1,5);
            notice(component3, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component3 = new ItemStack(Blocks.diamond_block);
            notice(component3, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component3 = new ItemStack(ModItems.missingitem);
            notice(component3, false);
        }       //Component3: Original: Unstable Block [ExtraUtils]; Fallback: Diamond Block [Minecraft]
        if (ModItemLibrary.starBlock != null) {
            component4 = new ItemStack(ModItemLibrary.starBlock);
            notice(component4, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component4 = new ItemStack(ModBlocks.netherstarblock);
            notice(component4, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component4 = new ItemStack(ModItems.missingitem);
            notice(component4, false);
        }           //Component4: Original: Nether Star Block [Forbidden Magic]; Fallback: Nether Star Block [CB]
        if (ModItemLibrary.gaiaIngot != null) {
            component5 = new ItemStack(ModItemLibrary.gaiaIngot, 1, 14);
            notice(component5, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component5 = new ItemStack(Blocks.end_stone);
            notice(component5, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component5 = new ItemStack(ModItems.missingitem);
            notice(component5, false);
        }           //Component5: Original: Gaia Ingot [Botania]; Fallback: End Stone [Minecraft]
        if (ModItemLibrary.brewOfFlowingSpirit != null) {
            component6 = new ItemStack(ModItemLibrary.brewOfFlowingSpirit, 1, 96);
            notice(component6, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component6 = new ItemStack(Items.potionitem, 1, 16417);
            notice(component6, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component6 = new ItemStack(ModItems.missingitem);
            notice(component6, false);
        } //Component6: Original: Brew Of FLowing Spirit [Witchery]; Fallback: Splash Potion of Regeneration II [Minecraft]
        if (ModItemLibrary.iridium != null) {
            component7 = new ItemStack(ModItemLibrary.iridium);
            notice(component7, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component7 = new ItemStack(Blocks.obsidian);
            notice(component7, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component7 = new ItemStack(ModItems.missingitem);
            notice(component7, false);
        }             //Component7: Original: Iridium Plate [Industrial Craft 2]; Fallback: Obsidian [Minecraft]
        if (ModItemLibrary.enderium != null) {
            component8 = new ItemStack(ModItemLibrary.enderium, 1, 12);
            notice(component8, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component8 = new ItemStack(Blocks.enchanting_table);
            notice(component8, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component8 = new ItemStack(ModItems.missingitem);
            notice(component8, false);
        }            //Component8: Original: Enderium Block [Thermal Foundation]; Fallback: Enchanting Table [Minecraft]

        //Is Vanilla
        if (Loader.isModLoaded("Extra Utilities") || Loader.isModLoaded("witchery") || Loader.isModLoaded("AWWayofTime") ||
                Loader.isModLoaded("TConstruct") || Loader.isModLoaded("ForbiddenMagic") || Loader.isModLoaded("Botania") ||
                Loader.isModLoaded("IC2") || Loader.isModLoaded("ThermalFoundation")) {
            component9 = new ItemStack(ModBlocks.comprpotatoblock, 1, 7);
            notice(component9, true);
        }
        else if (ConfigurationHandler.noSafeRecipe) {
            component9 = new ItemStack(ModBlocks.comprpotatoblock, 1, 3);
            notice(component9, false);
        }
        else if (!ConfigurationHandler.noSafeRecipe) {
            component8 = new ItemStack(ModItems.missingitem);
            notice(component9, false);
        }       //Component9: Original: Octuple Compressed Potato Block [CB]; Fallback: Quadruple Compressed Potato Block [CB]
    }

    private static void setRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.endgamium), component1, component2, component3, component4, component5, component6, component7, component8, component9);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.comprstarblock), "sss", "sss", "sss", 's', component4);
    }

    private static void notice(ItemStack itemStack, boolean moditem) {
        if (moditem)
            LogHelper.info("Registered crafting component \"" + itemStack.getDisplayName() + "\" from \"" + ToolTipHelper.getModName(itemStack.getItem()) + "\"");
        else if (!moditem && ConfigurationHandler.noSafeRecipe)
            LogHelper.info("Registered fallback crafting component \"" + itemStack.getDisplayName() + "\" from \"" + ToolTipHelper.getModName(itemStack.getItem()) + "\"");
        else if (!moditem && !ConfigurationHandler.noSafeRecipe) {
            LogHelper.warn("Fallback-recipes deactivated and recipe-component is missing.");
            LogHelper.info("Recipes will not work unless you enable the config option, add the missing mod or override the recipe with another mod.");
        }
    }
}