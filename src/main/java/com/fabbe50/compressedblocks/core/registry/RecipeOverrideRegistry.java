package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.lib.Configs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by fabbe50 on 12/02/2017.
 */
public class RecipeOverrideRegistry {
    /*public static void init() {
        if (Configs.hardcoreRecipes) {addModRecipes();}
        addVanillaRecipes();
    }

    private static void addVanillaRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.WOOL, 1, 0), new Object[] {"SS", "SS", 'S', new ItemStack(Items.STRING, 1, 0)});
        for (int i = 0; i < 16; ++i) {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.WOOL, 1, i), new Object[] {"dye" + OreDictRegistry.colorR[i], new ItemStack(Item.getItemFromBlock(Blocks.WOOL))}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 8, 15 - i), new Object[] {"###", "#X#", "###", '#', new ItemStack(Blocks.HARDENED_CLAY), 'X', "dye" + OreDictRegistry.color[i]}));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.STAINED_GLASS, 8, 15 - i), new Object[] {"###", "#X#", "###", '#', new ItemStack(Blocks.GLASS), 'X', "dye" + OreDictRegistry.color[i]}));
        }
    }

    private static void addModRecipes() {
        if (Loader.isModLoaded("extrautils2")) addEU2Recipes();
    }

    private static void addEU2Recipes() {
        GameRegistry.addShapedRecipe(new ItemStack(ModItemRegistry.TELEPORTER, 1, 1), new Object[]{
                "SOS",
                "ONO",
                "SOS",
                'S', new ItemStack(BlockRegistry.COMPRESSED_COBBLESTONE, 1, 6),
                'O', new ItemStack(BlockRegistry.COMPRESSED_COBBLESTONE, 1, 7),
                'N', new ItemStack(BlockRegistry.NETHER_STAR_BLOCK, 1, 0)
        });
        GameRegistry.addShapedRecipe(new ItemStack(ModItemRegistry.EUQUARRY, 1, 0), new Object[]{
                "ESE",
                "SNS",
                "ESE",
                'E', new ItemStack(Blocks.END_STONE, 1, 0),
                'S', new ItemStack(ModItemRegistry.DECORATIVE_SOLID, 1, 3),
                'N', new ItemStack(BlockRegistry.NETHER_STAR_BLOCK, 1, 0)
        });
        GameRegistry.addShapedRecipe(new ItemStack(ModItemRegistry.PASSIVEGENERATOR, 1, 6), new Object[]{
                "RNR",
                "EDE",
                "RNR",
                'R', new ItemStack(ModItemRegistry.RAINBOWGENERATOR, 1, 0),
                'N', new ItemStack(BlockRegistry.NETHER_STAR_BLOCK, 1, 0),
                'E', new ItemStack(BlockRegistry.ENDERBLOCK, 1, 0),
                'D', new ItemStack(ModItemRegistry.PASSIVEGENERATOR, 1, 8)
        });
        GameRegistry.addShapedRecipe(new ItemStack(ModItemRegistry.DECORATIVEBEDROCK, 4, 0), new Object[]{
                "BB",
                "BB",
                'B', new ItemStack(Blocks.BEDROCK, 1, 0)
        });
        GameRegistry.addShapedRecipe(new ItemStack(ModItemRegistry.DECORATIVEBEDROCK, 3, 1), new Object[]{
                "BBB",
                'B', new ItemStack(Blocks.BEDROCK, 1, 0)
        });
        GameRegistry.addShapedRecipe(new ItemStack(ModItemRegistry.DECORATIVEBEDROCK, 2, 2), new Object[]{
                "B",
                "B",
                'B', new ItemStack(Blocks.BEDROCK, 1, 0)
        });
    }*/
}
