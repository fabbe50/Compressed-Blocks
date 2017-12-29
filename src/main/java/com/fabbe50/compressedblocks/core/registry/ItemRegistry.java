package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.common.items.*;
import com.fabbe50.compressedblocks.common.items.base.ItemBase;
import com.fabbe50.compressedblocks.common.items.base.ItemBaseEnchanted;
import com.fabbe50.compressedblocks.common.items.base.ItemSwordBase;
import com.fabbe50.compressedblocks.common.items.base.TwoHandItemBase;
import com.fabbe50.compressedblocks.core.reference.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fabbe50 on 23/06/2016.
 */
public class ItemRegistry {
    public static final Item ENDGAMIUM_INGOT = new ItemBaseEnchanted("endgamiumingot", CBTab.itemTab);
    public static final Item ENDGAMIUM_NUGGET = new ItemBaseEnchanted("endgamiumnugget", CBTab.itemTab);
    public static final Item ENDGAMIUM_SWORD = new ItemSwordBase(ToolMaterialRegistry.TOOL_ENDGAMIUM, "endgamiumsword", CreativeTabs.COMBAT);
    public static final Item ENDGAMIUM_BONE = new ItemBaseEnchanted("endgamiumbone", CBTab.itemTab);
    public static final Item POTATO_BONE = new ItemPotatoBone("potatobone", CBTab.itemTab);
    public static final Item ITEM_FOOD = new ItemFood(20, 20.0f, false, "fooditem").setCreativeTab(CBTab.itemTab);
    public static final Item BEDROCK_BREAKER = new ItemBedrockBreaker("bedrockbreaker", CBTab.itemTab);
    public static final Item BEDROCK_OBTAINER = new ItemBedrockObtainer("bedrockobtainer", CBTab.itemTab);
    public static final Item BOW_ITEMS = new ItemBase("bowitems", CBTab.itemTab);
    public static final Item MILK_FLASK = new ItemMilkFlask("milkflask", CBTab.itemTab);
    public static final Item COLOR_CORE = new ItemBase("colorcore", CBTab.itemTab);
    public static final Item TWOHANDITEMTEST = new TwoHandItemBase("twohanditemtest", null).setCreativeTab(null);
    public static final Item TELEPORTORB = new ItemTeleportOrb("teleportorb", CBTab.itemTab);
    public static final Item MOBKILLER = new ItemMobKill(ToolMaterialRegistry.INSTAKILL, "mobkiller");
    public static final Item DNASAMPLE = new ItemDNASample("dnasample", null).setCreativeTab(null);
    public static final Item ENDERAPPLE = new ItemEnderApple(10, 15.0f, false, "enderapple").setAlwaysEdible().setCreativeTab(CBTab.itemTab);
    public static final Item PEBBLES = new ItemBase("pebble", CBTab.itemTab);
    public static final Item FOODBOWL = new ItemBowledFood(10, 15, false, "foodbowl").setMaxStackSize(16).setCreativeTab(CBTab.itemTab);
    public static final Item MASHEDFOOD = new ItemBowledFood(15, 25, true, "mashedfood").setMaxStackSize(16).setCreativeTab(CBTab.itemTab);
    public static final Item INKBOTTLE = new ItemInkBottle("inkbottle", CBTab.itemTab).setCreativeTab(CBTab.itemTab);
    public static final Item BEDROCK_INGOT = new ItemBase("bedrockingot", CBTab.itemTab);
    public static final Item STAFF_WOOD = new ItemStaff(1, 2, Item.ToolMaterial.WOOD, null, "wooden_staff").setCreativeTab(CBTab.itemTab);
    public static final Item STAFF_STONE = new ItemStaff(1, 2, Item.ToolMaterial.STONE, null, "stone_staff").setCreativeTab(CBTab.itemTab);
    public static final Item STAFF_IRON = new ItemStaff(1, 2, Item.ToolMaterial.IRON, null, "iron_staff").setCreativeTab(CBTab.itemTab);
    public static final Item STAFF_GOLD = new ItemStaff(1, 2, Item.ToolMaterial.GOLD, null, "gold_staff").setCreativeTab(CBTab.itemTab);
    public static final Item STAFF_DIAMOND = new ItemStaff(1, 2, Item.ToolMaterial.DIAMOND, null, "diamond_staff").setCreativeTab(CBTab.itemTab);
    public static final Item STAFF_ENDGAMIUM = new ItemStaff(1, 2, ToolMaterialRegistry.TOOL_ENDGAMIUM, null, "endgamium_staff").setCreativeTab(CBTab.itemTab);
    public static final Item EGG_HATCHER = new ItemEggHatcher("egghatcher", CBTab.itemTab);
    public static final Item POTATO_SINGULARITY = new ItemPotatoSingularity("potatosingularity", CBTab.itemTab);
    public static final Item TRINKET = new ItemTrinket("trinket", CBTab.itemTab).setCreativeTab(CBTab.itemTab);
    public static final Item INK_EXTRACTOR = new ItemInkExtractor("inkextr", CBTab.itemTab);
    //public static final Item COMPRESSEDSTICK;

    public static void init() {
        registerItem(ENDGAMIUM_INGOT);
        registerItem(ENDGAMIUM_NUGGET);
        registerItem(ENDGAMIUM_BONE);
        registerItem(ENDGAMIUM_SWORD);
        registerItem(POTATO_BONE);
        registerItem(ITEM_FOOD);
        registerItem(BEDROCK_BREAKER);
        registerItem(BEDROCK_OBTAINER);
        registerItem(BOW_ITEMS);
        registerItem(MILK_FLASK);
        registerItem(COLOR_CORE);
        registerItem(TWOHANDITEMTEST);
        registerItem(TELEPORTORB);
        registerItem(MOBKILLER);
        registerItem(DNASAMPLE);
        registerItem(ENDERAPPLE);
        registerItem(PEBBLES);
        registerItem(FOODBOWL);
        registerItem(MASHEDFOOD);
        registerItem(INKBOTTLE);
        registerItem(BEDROCK_INGOT);
        registerItem(STAFF_WOOD);
        registerItem(STAFF_STONE);
        registerItem(STAFF_IRON);
        registerItem(STAFF_GOLD);
        registerItem(STAFF_DIAMOND);
        registerItem(STAFF_ENDGAMIUM);
        registerItem(EGG_HATCHER);
        registerItem(POTATO_SINGULARITY);
        registerItem(TRINKET);
        registerItem(INK_EXTRACTOR);
    }

    public static void registerItem(Item item) {
        ItemRegistrationHandler.items.add(item);
    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID)
    public static class ItemRegistrationHandler {
        public static final Set<Item> ITEM_LIST = new HashSet<>();
        private static final Set<Item> registeredItemList = new HashSet<>();
        public static final List<Item> items = new ArrayList<>();

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> reg = event.getRegistry();
            for (Item item : items) {
                reg.register(item);
                ITEM_LIST.add(item);
            }
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            for (int i = 0; i < EnumDyeColor.values().length; i++) {
                registerItemModel(ItemRegistry.INKBOTTLE, i, new ModelResourceLocation(Reference.MOD_ID + ":ink_" + EnumDyeColor.byDyeDamage(i).getName(), "inventory"));
            }

            registerItemModel(ItemRegistry.ENDERAPPLE, 0);
            registerItemModel(ItemRegistry.ENDERAPPLE, 1);
            registerItemModel(ItemRegistry.TRINKET, 0, "trinket0");
            registerItemModel(ItemRegistry.TRINKET, 1, "trinket");
            registerItemModel(ItemRegistry.TRINKET, 2, "trinket1");
            registerItemModel(ItemRegistry.TRINKET, 3, "trinket2");
            registerItemModel(ItemRegistry.TRINKET, 4, "portablebeacon");

            for (Item item : ITEM_LIST) {
                registerItemModel(item);
            }
        }

        private static void registerItemModel(Item item) {
            String registryName = item.getRegistryName().toString();
            ModelResourceLocation location = new ModelResourceLocation(registryName, "inventory");
            registerItemModel(item, 0, location);
        }

        private static void registerItemModel(Item item, int meta) {
            registerItemModel(item, meta, new ModelResourceLocation(item.getRegistryName().toString()));
        }

        private static void registerItemModel(Item item, int meta, String name) {
            registerItemModel(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
        }

        private static void registerItemModel(Item item, int meta, ModelResourceLocation location) {
            if (!registeredItemList.contains(item) || meta > 0) {
                ModelLoader.setCustomModelResourceLocation(item, meta, location);
                if (!registeredItemList.contains(item))
                    registeredItemList.add(item);
            }
        }
    }
}
