package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.items.*;
import com.fabbe50.compressedblocks.common.items.base.ItemBaseEnchanted;
import com.fabbe50.compressedblocks.common.items.base.ItemSwordBase;
import com.fabbe50.compressedblocks.common.items.base.TwoHandItemBase;
import com.fabbe50.compressedblocks.core.reference.MetaValues;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.init.TFIItems;
import com.thefifthidiot.tficore.render.ItemRenderer;
import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by fabbe50 on 23/06/2016.
 */
public class ItemRegistry {
    public static final Item ENDGAMIUM_INGOT;
    public static final Item ENDGAMIUM_NUGGET;
    public static final Item ENDGAMIUM_SWORD;
    public static final Item ENDGAMIUM_BONE;
    public static final Item POTATO_BONE;
    public static final Item ITEM_FOOD;
    public static final Item BEDROCK_BREAKER;
    public static final Item BEDROCK_OBTAINER;
    public static final Item BOW_ITEMS;
    public static final Item MILK_FLASK;
    public static final Item COLOR_CORE;
    public static final Item TWOHANDITEMTEST;
    public static final Item TELEPORTORB;
    public static final Item MOBKILLER;
    public static final Item DNASAMPLE;
    public static final Item ENDERAPPLE;
    public static final Item PEBBLES;
    public static final Item FOODBOWL;
    public static final Item MASHEDFOOD;
    public static final Item INKBOTTLE;
    public static final Item BEDROCK_INGOT;
    //public static final Item COMPRESSEDSTICK;

    static {
        ENDGAMIUM_INGOT = TFIItems.registerItem(new ItemBaseEnchanted("endgamiumingot", null));
        ENDGAMIUM_NUGGET = TFIItems.registerItem(new ItemBaseEnchanted("endgamiumnugget", null));
        ENDGAMIUM_SWORD = TFIItems.registerItem(new ItemSwordBase(ToolMaterialRegistry.TOOL_ENDGAMIUM, "endgamiumsword", CreativeTabs.COMBAT));
        ENDGAMIUM_BONE = TFIItems.registerItem(new ItemBaseEnchanted("endgamiumbone", null));
        POTATO_BONE = TFIItems.registerItem(new ItemPotatoBone("potatobone", null));
        ITEM_FOOD = TFIItems.registerItem(new ItemFood(20, 20.0f, false, "fooditem"));
        BEDROCK_BREAKER = TFIItems.registerItem(new ItemBedrockBreaker("bedrockbreaker", null));
        BEDROCK_OBTAINER = TFIItems.registerItem(new ItemBedrockObtainer("bedrockobtainer", null));
        BOW_ITEMS = TFIItems.registerItem(new ItemBase("bowitems", null));
        MILK_FLASK = TFIItems.registerItem(new ItemMilkFlask("milkflask", null));
        COLOR_CORE = TFIItems.registerItem(new ItemBase("colorcore", null));
        TWOHANDITEMTEST = TFIItems.registerItem(new TwoHandItemBase("twohanditemtest", null)).setCreativeTab(null);
        TELEPORTORB = TFIItems.registerItem(new ItemTeleportOrb("teleportorb", null));
        MOBKILLER = TFIItems.registerItem(new ItemMobKill(ToolMaterialRegistry.INSTAKILL, "mobkiller"));
        DNASAMPLE = TFIItems.registerItem(new ItemDNASample("dnasample", null)).setCreativeTab(null);
        ENDERAPPLE = TFIItems.registerItem(new ItemEnderApple(10, 15.0f, false, "enderapple").setAlwaysEdible());
        PEBBLES = TFIItems.registerItem(new ItemBase("pebble", null));
        FOODBOWL = TFIItems.registerItem(new ItemBowledFood(10, 15, false, "foodbowl")).setMaxStackSize(16);
        MASHEDFOOD = TFIItems.registerItem(new ItemBowledFood(15, 25, true, "mashedfood")).setMaxStackSize(16);
        INKBOTTLE = TFIItems.registerItem(new ItemInkBottle("inkbottle", null)).setCreativeTab(null);
        BEDROCK_INGOT = TFIItems.registerItem(new ItemBase("bedrockingot", null));
        //COMPRESSEDSTICK = TFIItems.registerItem(new CompressedItemBase("stick_compr", null));
    }

    public static void renderInit() {
        ItemRenderer.registerItem(ENDGAMIUM_INGOT);
        ItemRenderer.registerItem(ENDGAMIUM_NUGGET);
        ItemRenderer.registerItem(ENDGAMIUM_BONE);
        ItemRenderer.registerItem(ENDGAMIUM_SWORD);
        ItemRenderer.registerItem(POTATO_BONE);
        ItemRenderer.registerItem(ITEM_FOOD);
        ItemRenderer.registerItem(BEDROCK_BREAKER);
        ItemRenderer.registerItem(BEDROCK_OBTAINER);
        ItemRenderer.registerItem(BOW_ITEMS);
        ItemRenderer.registerItem(MILK_FLASK);
        ItemRenderer.registerItem(COLOR_CORE);
        ItemRenderer.registerItem(TWOHANDITEMTEST);
        ItemRenderer.registerItem(TELEPORTORB);
        ItemRenderer.registerItem(MOBKILLER);
        ItemRenderer.registerItem(DNASAMPLE);
        ItemRenderer.registerItem(ENDERAPPLE);
        registerItem(ENDERAPPLE, 1);
        ItemRenderer.registerItem(PEBBLES);
        ItemRenderer.registerItem(FOODBOWL);
        ItemRenderer.registerItem(MASHEDFOOD);
        ItemRenderer.registerItem(BEDROCK_INGOT);

        /*registerItem(INKBOTTLE, EnumDyeColor.BLACK.getDyeDamage(), "ink_black");
        registerItem(INKBOTTLE, EnumDyeColor.RED.getDyeDamage(), "ink_red");
        registerItem(INKBOTTLE, EnumDyeColor.GREEN.getDyeDamage(), "ink_green");
        registerItem(INKBOTTLE, EnumDyeColor.BROWN.getDyeDamage(), "ink_brown");
        registerItem(INKBOTTLE, EnumDyeColor.BLUE.getDyeDamage(), "ink_blue");
        registerItem(INKBOTTLE, EnumDyeColor.PURPLE.getDyeDamage(), "ink_purple");
        registerItem(INKBOTTLE, EnumDyeColor.CYAN.getDyeDamage(), "ink_cyan");
        registerItem(INKBOTTLE, EnumDyeColor.SILVER.getDyeDamage(), "ink_silver");
        registerItem(INKBOTTLE, EnumDyeColor.GRAY.getDyeDamage(), "ink_gray");
        registerItem(INKBOTTLE, EnumDyeColor.PINK.getDyeDamage(), "ink_pink");
        registerItem(INKBOTTLE, EnumDyeColor.LIME.getDyeDamage(), "ink_lime");
        registerItem(INKBOTTLE, EnumDyeColor.YELLOW.getDyeDamage(), "ink_yellow");
        registerItem(INKBOTTLE, EnumDyeColor.LIGHT_BLUE.getDyeDamage(), "ink_light_blue");
        registerItem(INKBOTTLE, EnumDyeColor.MAGENTA.getDyeDamage(), "ink_magenta");
        registerItem(INKBOTTLE, EnumDyeColor.ORANGE.getDyeDamage(), "ink_orange");
        registerItem(INKBOTTLE, EnumDyeColor.WHITE.getDyeDamage(), "ink_white");*/

        registerVariantItemModels(INKBOTTLE, MetaValues.COLORS);

        //registerVariantItemModels(COMPRESSEDSTICK, "compression", EnumCompressionRate.values());
    }

    public static void init() {
    }

    //INFO: MOVED TO TFICORE
    private static void registerVariantItemModels(Item item, String[] variants) {
        ResourceLocation[] locations = new ResourceLocation[16];
        for(int i = 0; i < variants.length; i++){
            locations[i] = new ResourceLocation(Reference.MOD_ID, item.getRegistryName().getResourcePath() + "_" + variants[i]);
            LogHelper.info(new ResourceLocation(Reference.MOD_ID, item.getRegistryName().getResourcePath() + "_" + variants[i]));
            ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(locations[i], "inventory"));
            LogHelper.info(new ModelResourceLocation(locations[i], "inventory"));
        }
        ModelBakery.registerItemVariants(item, locations);
    }

    private static void registerItem(Item item, int meta, String identifier) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, identifier), "inventory"));
        LogHelper.info(new ModelResourceLocation(item.getRegistryName(), identifier));
    }

    private static void registerItem(Item item, int meta) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        LogHelper.info("Registered renderdata for item with registry-name: " + item.getRegistryName());
    }
}
