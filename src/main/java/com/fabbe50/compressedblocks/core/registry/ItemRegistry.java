package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.items.ItemFood;
import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.init.TFIItems;
import com.thefifthidiot.tficore.render.ItemRenderer;
import net.minecraft.item.Item;

/**
 * Created by fabbe50 on 23/06/2016.
 */
public class ItemRegistry {
    public static final Item endgamiumIngot;
    public static final Item endgamiumNugget;
    //public static final Item endgamiumSword;
    public static final Item endgamiumBone;
    public static final Item potatoBone;
    public static final Item itemFood;

    static {
        endgamiumIngot = TFIItems.registerItem(new ItemBase("endgamiumIngot", null));
        endgamiumNugget = TFIItems.registerItem(new ItemBase("endgamiumNugget", null));
        endgamiumBone = TFIItems.registerItem(new ItemBase("endgamiumBone", null));
        potatoBone = TFIItems.registerItem(new ItemBase("potatoBone", null));
        itemFood = TFIItems.registerItem(new ItemFood(20, 20.0f, false, "foodItem"));
    }

    public static void renderInit() {
        ItemRenderer.registerItem(endgamiumIngot);
        ItemRenderer.registerItem(endgamiumNugget);
        ItemRenderer.registerItem(endgamiumBone);
        ItemRenderer.registerItem(potatoBone);
        ItemRenderer.registerItem(itemFood);
    }

    public static void init() {
    }
}
