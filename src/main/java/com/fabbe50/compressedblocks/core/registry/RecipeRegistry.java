package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.lib.recipes.MiniBeaconRecipes;
import com.fabbe50.compressedblocks.core.reference.MetaValues;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by fabbe50 on 16/09/2016.
 */
public class RecipeRegistry {
    private static ItemEnchantedBook enchantedBook;
    private static ItemStack mendingBook = new ItemStack(Items.ENCHANTED_BOOK, 1, 0);
    private static NBTTagList bookTags;
    private static NBTTagCompound bookTag;
    private static ItemStack endgamiumSword = new ItemStack(ItemRegistry.ENDGAMIUM_SWORD, 1, 0);

    public static void init() {
        addDataToItemStack();

        createReturnableRecipes();
        createShapedOreRecipes();
        createShapedRecipes();
        createShapelessRecipes();
        createHalfShapedRecipes();
        createShapedHCRecipes();
        createUncraft3x3Recipes();
        createSpecialRecipes();

        registerReturnableRecipes();
    }

    private static void addDataToItemStack() {
        //mendingBook.addEnchantment(Enchantment.getEnchantmentByID(70), 1);
        Items.ENCHANTED_BOOK.addEnchantment(mendingBook, new EnchantmentData(Enchantment.getEnchantmentByID(70), 1));
        endgamiumSword.addEnchantment(Enchantment.getEnchantmentByID(16), 5);
        endgamiumSword.addEnchantment(Enchantment.getEnchantmentByID(21), 3);
        endgamiumSword.addEnchantment(Enchantment.getEnchantmentByID(22), 3);
        endgamiumSword.addEnchantment(Enchantment.getEnchantmentByID(34), 3);
        endgamiumSword.addEnchantment(Enchantment.getEnchantmentByID(19), 2);
    }

    private static void createReturnableRecipes() {
        ModRegistry.addReturnRecipe(new ItemStack(ItemRegistry.MASHEDFOOD, 1, 0), new Object[]{new ItemStack(ItemRegistry.FOODBOWL, 1, 0), new ItemStack(ItemRegistry.PEBBLES, 1, 0), new ItemStack(Items.POTIONITEM, 1, 0)});
    }

    private static void createSpecialRecipes() {
        MiniBeaconRecipes.acceptedItems.add(Items.POTIONITEM);
        MiniBeaconRecipes.acceptedItems.add(Items.SHIELD);
        MiniBeaconRecipes.acceptedItems.add(ItemRegistry.ITEM_FOOD);
        GameRegistry.addRecipe(new MiniBeaconRecipes());
    }

    private static void createShapedRecipes() {
        //3x3 Item Crafting
        craft3x3Block(ItemRegistry.ENDGAMIUM_NUGGET, 1, 0, ItemRegistry.ENDGAMIUM_INGOT, 1, 0, false);

        //Normal Blocks Recipes
        craft3x3Block(Items.POTATO, 1, 0, BlockRegistry.POTATO_BLOCK, 1, 0, false);
        craft3x3Block(Blocks.FURNACE, 1, 0, BlockRegistry.COMPRESSED_FURNACE_IDLE, 1, 0, false);
        craft3x3Block(Items.NETHER_STAR, 1, 0, BlockRegistry.NETHER_STAR_BLOCK, 1, 0, false);
        craft3x3Block(ItemRegistry.ENDGAMIUM_INGOT, 1, 0, BlockRegistry.ENDGAMIUMBLOCK, 1, 0, false);
        craft3x3Block(BlockRegistry.ENDGAMIUMBLOCK, 1, 0, BlockRegistry.ENDGAMIUMBLOCKC, 1, 0, false);
        craft3x3Block(Blocks.TNT, 1, 0, BlockRegistry.SINGLECOMPRESSSEDTNT, 1, 0, false);
        craft3x3Block(BlockRegistry.SINGLECOMPRESSSEDTNT, 1, 0, BlockRegistry.DOUBLECOMPRESSSEDTNT, 1, 0, false);
        craft3x3Block(BlockRegistry.DOUBLECOMPRESSSEDTNT, 1, 0, BlockRegistry.TRIPLECOMPRESSSEDTNT, 1, 0, false);
        craft3x3Block(Items.ENDER_PEARL, 1, 0, BlockRegistry.ENDERBLOCK, 1, 0, false);
        craft3x3Block(ItemRegistry.PEBBLES, 1, 0, Blocks.COBBLESTONE, 1, 0, false);
        craft3x3Block(ItemRegistry.BEDROCK_INGOT, 1, 0, Blocks.BEDROCK, 1, 0, false);

        //Compressed Blocks Recipes
        craft3x3Block(BlockRegistry.POTATO_BLOCK, 1, 0, BlockRegistry.COMPRESSED_POTATO, 1, 0, true);
        craft3x3Block(Blocks.COBBLESTONE, 1, 0, BlockRegistry.COMPRESSED_COBBLESTONE, 1, 0, true);
        craft3x3Block(Blocks.GRAVEL, 1, 0, BlockRegistry.COMPRESSED_GRAVEL, 1, 0, true);
        craft3x3Block(Blocks.SAND, 1, 0, BlockRegistry.COMPRESSED_SAND, 1, 0, true);
        craft3x3Block(Blocks.DIRT, 1, 0, BlockRegistry.COMPRESSED_DIRT, 1, 0, true);
        craft3x3Block(Blocks.EMERALD_BLOCK, 1, 0, BlockRegistry.COMPRESSED_EMERALD, 1, 0, true);
        craft3x3Block(Blocks.DIAMOND_BLOCK, 1, 0, BlockRegistry.COMPRESSED_DIAMOND, 1, 0, true);
        craft3x3Block(Blocks.GOLD_BLOCK, 1, 0, BlockRegistry.COMPRESSED_GOLD, 1, 0, true);
        craft3x3Block(Blocks.IRON_BLOCK, 1, 0, BlockRegistry.COMPRESSED_IRON, 1, 0, true);
        craft3x3Block(Blocks.GRASS, 1, 0, BlockRegistry.COMPRESSED_GRASS, 1, 0, true);
    }

    private static void createUncraft3x3Recipes() {
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_POTATO), Item.getItemFromBlock(BlockRegistry.POTATO_BLOCK));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_COBBLESTONE), Item.getItemFromBlock(Blocks.COBBLESTONE));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_GRAVEL), Item.getItemFromBlock(Blocks.GRAVEL));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_SAND), Item.getItemFromBlock(Blocks.SAND));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_DIRT), Item.getItemFromBlock(Blocks.DIRT));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_EMERALD), Item.getItemFromBlock(Blocks.EMERALD_BLOCK));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_DIAMOND), Item.getItemFromBlock(Blocks.DIAMOND_BLOCK));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_GOLD), Item.getItemFromBlock(Blocks.GOLD_BLOCK));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_IRON), Item.getItemFromBlock(Blocks.IRON_BLOCK));
        uncraft3x3(Item.getItemFromBlock(BlockRegistry.COMPRESSED_GRASS), Item.getItemFromBlock(Blocks.GRASS));
    }

    private static void createShapedOreRecipes() {
        craft3x3OreDictCompressed("Cobble", BlockRegistry.COMPRESSED_COBBLESTONE);
        craft3x3OreDictCompressed("Dirt", BlockRegistry.COMPRESSED_DIRT);
        craft3x3OreDictCompressed("Gravel", BlockRegistry.COMPRESSED_GRAVEL);
        craft3x3OreDictCompressed("Sand", BlockRegistry.COMPRESSED_SAND);
    }

    private static void createShapelessRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ENDGAMIUM_NUGGET, 9, 0), new Object[]{new ItemStack(ItemRegistry.ENDGAMIUM_INGOT, 1, 0)});
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ENDGAMIUM_BONE, 1, 0), new Object[]{new ItemStack(ItemRegistry.ENDGAMIUM_NUGGET, 1, 0), new ItemStack(Items.BONE, 1, 0)});
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.POTATO_BONE, 1, 0), new Object[]{new ItemStack(ItemRegistry.ENDGAMIUM_BONE, 1, 0), new ItemStack(Items.BAKED_POTATO, 1, 0)});
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.FALLTRAPBLOCK, 1, 0), new Object[]{new ItemStack(Items.ENDER_PEARL, 1, 0), new ItemStack(Blocks.COBBLESTONE, 1, 0), new ItemStack(Blocks.TRIPWIRE_HOOK, 1, 0)});
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ENDGAMIUM_INGOT, 2, 0), new Object[]{new ItemStack(ItemRegistry.ENDGAMIUM_INGOT, 1, 0), new ItemStack(Items.IRON_INGOT, 1, 0), new ItemStack(Items.NETHER_STAR, 1 ,0)});
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ENDGAMIUM_INGOT, 1, 0), new Object[]{new ItemStack(BlockRegistry.NETHER_STAR_BLOCK, 1, 0), new ItemStack(BlockRegistry.COMPRESSED_IRON, 1, 0), new ItemStack(Items.GOLDEN_APPLE, 1, 1), new ItemStack(Items.END_CRYSTAL, 1, 0), new ItemStack(Blocks.DRAGON_EGG), new ItemStack(ItemRegistry.ENDERAPPLE, 1, 1)});
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.FOODBOWL, 1, 0), new Object[]{new ItemStack(Items.BOWL, 1, 0), new ItemStack(Items.COOKED_BEEF, 1, 0), new ItemStack(Items.COOKED_PORKCHOP, 1, 0), new ItemStack(Items.COOKED_FISH, 1, 0), new ItemStack(Items.BAKED_POTATO, 1, 0), new ItemStack(Items.CARROT, 1, 0), new ItemStack(Items.BREAD, 1, 0)});
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.PEBBLES, 9, 0), new Object[]{new ItemStack(Blocks.COBBLESTONE, 1, 0)});
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.TELEPORTORB, 1, 0), new Object[]{new ItemStack(ItemRegistry.ENDERAPPLE, 1, 1), new ItemStack(Items.COMPASS, 1, 0), new ItemStack(Items.COMPASS, 1, 0), new ItemStack(Blocks.OBSIDIAN, 1, 0), new ItemStack(Blocks.OBSIDIAN, 1, 0), new ItemStack(Blocks.OBSIDIAN, 1, 0), new ItemStack(Items.DIAMOND, 1, 0), new ItemStack(Items.DYE, 1, 4), new ItemStack(Items.DYE, 1, 4)});
    }

    private static void createShapedHCRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(Blocks.DISPENSER, 1, 0), new Object[]{
                "CCC",
                "CPC",
                "CRC",
                'C', new ItemStack(Blocks.COBBLESTONE, 1, 0),
                'R', new ItemStack(Items.REDSTONE),
                'P', new ItemStack(ItemRegistry.BOW_ITEMS)});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.COMPRESSED_FURNACE_IDLE, 1, 0), new Object[]{
                "CCC",
                "C C",
                "CCC",
                'C', new ItemStack(BlockRegistry.COMPRESSED_COBBLESTONE, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.BEACON_XRAY, 1, 0), new Object[]{
                "WWW",
                "WNW",
                "OOO",
                'W', new ItemStack(Blocks.STAINED_GLASS, 1, 0),
                'N', new ItemStack(BlockRegistry.NETHER_STAR_BLOCK, 1, 0),
                'O', new ItemStack(Blocks.OBSIDIAN, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.BEDROCK_BREAKER, 1, 0), new Object[]{
                "  N",
                " B ",
                "S  ",
                'N', new ItemStack(Items.NETHER_STAR, 1, 0),
                'B', new ItemStack(Items.BLAZE_ROD, 1, 0),
                'S', new ItemStack(Items.STICK, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.BEDROCK_OBTAINER, 1, 0), new Object[]{
                "NNN",
                "NBN",
                "NNN",
                'N', new ItemStack(Items.NETHER_STAR, 1, 0),
                'B', new ItemStack(ItemRegistry.BEDROCK_BREAKER, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.COLOR_CORE, 1, 0), new Object[]{
                " R ",
                "GCB",
                " I ",
                'R', new ItemStack(Items.DYE, 1, 14),
                'G', new ItemStack(Items.DYE, 1, 13),
                'B', new ItemStack(Items.DYE, 1, 11),
                'I', new ItemStack(Items.DYE, 1, 15),
                'C', new ItemStack(Blocks.REDSTONE_BLOCK, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.COLORBLOCK, 32, 0), new Object[]{
                "WWW",
                "SCS",
                "WWW",
                'W', new ItemStack(Blocks.WOOL, 1),
                'S', new ItemStack(BlockRegistry.COMPRESSED_COBBLESTONE, 1, 0),
                'C', new ItemStack(ItemRegistry.COLOR_CORE, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.MILK_FLASK, 8, 0), new Object[]{
                "BBB",
                "BMB",
                "BBB",
                'B', new ItemStack(Items.GLASS_BOTTLE, 1, 0),
                'M', new ItemStack(Items.MILK_BUCKET, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.MININGEXPLOSIVES, 4, 0), new Object[]{
                "TCT",
                "SPS",
                "TDT",
                'T', new ItemStack(BlockRegistry.TRIPLECOMPRESSSEDTNT, 1, 0),
                'C', new ItemStack(Blocks.CHEST, 1, 0),
                'S', new ItemStack(Items.WHEAT_SEEDS, 1, 0),
                'P', new ItemStack(Items.DIAMOND_PICKAXE, 1, 0),
                'D', new ItemStack(BlockRegistry.COMPRESSED_DIRT, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.ENDERAPPLE, 1, 0), new Object[]{
                "EEE",
                "EAE",
                "EEE",
                'E', new ItemStack(Items.ENDER_PEARL, 1, 0),
                'A', new ItemStack(Items.APPLE, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.ENDERAPPLE, 1, 1), new Object[]{
                "EEE",
                "EAE",
                "EEE",
                'E', new ItemStack(BlockRegistry.ENDERBLOCK, 1, 0),
                'A', new ItemStack(Items.APPLE, 1, 0)});
        GameRegistry.addShapedRecipe(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM, 8), PotionTypes.WATER), new Object[]{
                "GGG",
                "GBG",
                "GGG",
                'G', new ItemStack(Items.GLASS_BOTTLE),
                'B', new ItemStack(Items.WATER_BUCKET)});
        GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.FUSIONPEDESTAL, 1, 0), new Object[] {
                " B ",
                "EPE",
                "OOO",
                'B', mendingBook,
                'E', new ItemStack(Items.EMERALD, 1, 0),
                'P', new ItemStack(BlockRegistry.ENDERBLOCK, 1, 0),
                'O', new ItemStack(Blocks.OBSIDIAN, 1, 0)});
        GameRegistry.addShapedRecipe(endgamiumSword, new Object[] {
                "E",
                "E",
                "S",
                'E', new ItemStack(ItemRegistry.ENDGAMIUM_INGOT, 1, 0),
                'S', new ItemStack(Items.STICK, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.TRINKET, 1, 4), new Object[] {
                " i ",
                "IBI",
                " i ",
                'i', new ItemStack(Blocks.IRON_BLOCK, 1, 0),
                'I', new ItemStack(BlockRegistry.COMPRESSED_IRON, 1, 1),
                'B', new ItemStack(Items.DIAMOND, 1, 0)});
    }

    private static void createHalfShapedRecipes() {
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.BOW_ITEMS, 1, 0), new Object[]{"SSS", "TTT", 'S', new ItemStack(Items.STRING, 1, 0), 'T', new ItemStack(Items.STICK, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.BOW_ITEMS, 1, 0), new Object[]{"TTT", "SSS", 'S', new ItemStack(Items.STRING, 1, 0), 'T', new ItemStack(Items.STICK, 1, 0)});
        GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.BOW_ITEMS, 1, 0), new Object[]{"ST", "ST", "ST", 'S', new ItemStack(Items.STRING, 1, 0), 'T', new ItemStack(Items.STICK, 1, 0)});
    }

    private static void vanillaHooksRecipes() {
        if (Configs.vanillaHooks && Configs.stackSizes)
            GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.BREWER, 1, 0), new Object[]{" B ", "CCC", 'B', new ItemStack(Items.BLAZE_ROD), 'C', new ItemStack(Blocks.COBBLESTONE)});
            GameRegistry.addShapelessRecipe(new ItemStack(Blocks.BREWING_STAND, 1, 0), new Object[]{new ItemStack(BlockRegistry.BREWER, 1, 0)});
    }

    private static void registerReturnableRecipes() {
        CraftingManager manager = CraftingManager.getInstance();
        for (IRecipe recipe : ModRegistry.returnRecipes) {
            manager.addRecipe(recipe);
        }
    }

    //Simple infrastructure for 3x3 crafting
    private static void craft3x3Block(Block blockIn, int amountIn, int metaIn, Block blockOut, int amountOut, int metaOut, boolean compressed) {
        craft3x3Block(Item.getItemFromBlock(blockIn), amountIn, metaIn, Item.getItemFromBlock(blockOut), amountOut, metaOut, compressed);
    }

    private static void craft3x3Block(Block blockIn, int amountIn, int metaIn, Item itemOut, int amountOut, int metaOut, boolean compressed) {
        craft3x3Block(Item.getItemFromBlock(blockIn), amountIn, metaIn, itemOut, amountOut, metaOut, compressed);
    }

    private static void craft3x3Block(Item itemIn, int amountIn, int metaIn, Block blockOut, int amountOut, int metaOut, boolean compressed) {
        craft3x3Block(itemIn, amountIn, metaIn, Item.getItemFromBlock(blockOut), amountOut, metaOut, compressed);
    }

    private static void craft3x3Block(Item itemIn, int amountIn, int metaIn, Item itemOut, int amountOut, int metaOut, boolean compressed) {
        if (!compressed) {
            GameRegistry.addRecipe(new ItemStack(itemOut, amountOut, metaOut), new Object[]{
                    "CCC",
                    "CCC",
                    "CCC",
                    'C', new ItemStack(itemIn, amountIn, metaIn)
            });
        }
        else if (compressed) {
            for (int i = 0; i < 7; i++) {
                if (i == 0) {
                    GameRegistry.addRecipe(new ItemStack(itemOut, 1, i), new Object[]{
                            "CCC",
                            "CCC",
                            "CCC",
                            'C', itemIn
                    });
                }
                GameRegistry.addRecipe(new ItemStack(itemOut, 1, i + 1), new Object[]{
                        "CCC",
                        "CCC",
                        "CCC",
                        'C', new ItemStack(itemOut, 1, i)
                });
            }
        }
    }
    
    private static void craft3x3OreDictCompressed(String oreDictEntryIn, Block blockOut) {
        for (int i = 0; i < 7; i++) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockOut, 1, i + 1), new Object[]{
                    "CCC",
                    "CCC",
                    "CCC",
                    'C', MetaValues.COMPRESSED[i] + oreDictEntryIn
            }));
        }
    }

    private static void uncraft3x3(Item in, Item out) {
        for (int i = 6; i >= 0; i--) {
            GameRegistry.addShapelessRecipe(new ItemStack(in, 9, i), new Object[]{new ItemStack(in, 1, i + 1)});
        }
        GameRegistry.addShapelessRecipe(new ItemStack(out, 9, 0), new Object[]{new ItemStack(in, 1, 0)});
    }
}
