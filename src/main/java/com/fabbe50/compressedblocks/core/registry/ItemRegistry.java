package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.items.*;
import com.fabbe50.compressedblocks.common.items.base.ItemBaseEnchanted;
import com.fabbe50.compressedblocks.common.items.base.TwoHandItemBase;
import com.fabbe50.compressedblocks.core.utils.IVariant;
import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.init.TFIItems;
import com.thefifthidiot.tficore.render.ItemRenderer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fabbe50 on 23/06/2016.
 */
public class ItemRegistry {
    public static final Item ENDGAMIUM_INGOT;
    public static final Item ENDGAMIUM_NUGGET;
    //public static final Item ENDGAMIUM_SWORD;
    public static final Item ENDGAMIUM_BONE;
    public static final Item POTATO_BONE;
    public static final Item ITEM_FOOD;
    public static final Item BEDROCK_BREAKER;
    public static final Item BEDROCK_OBTAINER;
    public static final Item BOW_ITEMS;
    public static final Item MILK_FLASK;
    public static final Item COLOR_CORE;
    public static final Item TWOHANDITEMTEST;
    //public static final Item COMPRESSEDSTICK;

    static {
        ENDGAMIUM_INGOT = TFIItems.registerItem(new ItemBaseEnchanted("endgamiumIngot", null));
        ENDGAMIUM_NUGGET = TFIItems.registerItem(new ItemBaseEnchanted("endgamiumNugget", null));
        ENDGAMIUM_BONE = TFIItems.registerItem(new ItemBaseEnchanted("endgamiumBone", null));
        POTATO_BONE = TFIItems.registerItem(new ItemPotatoBone("potatoBone", null));
        ITEM_FOOD = TFIItems.registerItem(new ItemFood(20, 20.0f, false, "foodItem"));
        BEDROCK_BREAKER = TFIItems.registerItem(new ItemBedrockBreaker("bedrockBreaker", null));
        BEDROCK_OBTAINER = TFIItems.registerItem(new ItemBedrockObtainer("bedrockobtainer", null));
        BOW_ITEMS = TFIItems.registerItem(new ItemBase("bowitems", null));
        MILK_FLASK = TFIItems.registerItem(new ItemMilkFlask("milkflask", null));
        COLOR_CORE = TFIItems.registerItem(new ItemBase("colorcore", null));
        TWOHANDITEMTEST = TFIItems.registerItem(new TwoHandItemBase("twohanditemtest", null)).setCreativeTab(null);
        //COMPRESSEDSTICK = TFIItems.registerItem(new CompressedItemBase("stick_compr", null));
    }

    public static void renderInit() {
        ItemRenderer.registerItem(ENDGAMIUM_INGOT);
        ItemRenderer.registerItem(ENDGAMIUM_NUGGET);
        ItemRenderer.registerItem(ENDGAMIUM_BONE);
        ItemRenderer.registerItem(POTATO_BONE);
        ItemRenderer.registerItem(ITEM_FOOD);
        ItemRenderer.registerItem(BEDROCK_BREAKER);
        ItemRenderer.registerItem(BEDROCK_OBTAINER);
        ItemRenderer.registerItem(BOW_ITEMS);
        ItemRenderer.registerItem(MILK_FLASK);
        ItemRenderer.registerItem(COLOR_CORE);
        ItemRenderer.registerItem(TWOHANDITEMTEST);

        //registerVariantItemModels(COMPRESSEDSTICK, "compression", EnumCompressionRate.values());
    }

    public static void init() {
    }
}
