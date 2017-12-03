package com.fabbe50.compressedblocks.core.utils;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.utils.helper.ChatHelper;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 09/05/2017.
 */
public class VanillaHooks {
    private static List<Item> items = new ArrayList<>();

    public static void init() {
        if (Configs.vanillaHooks) {
            changeStackSizes();
            changeEntityLoot();
        }
    }

    private static void changeStackSizes() {
        if (Configs.stackSizes) {
            if (!Loader.isModLoaded("quark")) {
                Items.MINECART.setMaxStackSize(16);
                Items.CHEST_MINECART.setMaxStackSize(16);
                Items.FURNACE_MINECART.setMaxStackSize(16);
                Items.HOPPER_MINECART.setMaxStackSize(16);
                Items.TNT_MINECART.setMaxStackSize(16);
                Items.COMMAND_BLOCK_MINECART.setMaxStackSize(16);
            } else {
                LogHelper.info("Found 'Quark', disabling similar features.");}

            Items.BOAT.setMaxStackSize(16);
            Items.BIRCH_BOAT.setMaxStackSize(16);
            Items.ACACIA_BOAT.setMaxStackSize(16);
            Items.DARK_OAK_BOAT.setMaxStackSize(16);
            Items.JUNGLE_BOAT.setMaxStackSize(16);
            Items.SPRUCE_BOAT.setMaxStackSize(16);
            Items.SADDLE.setMaxStackSize(16);
            Items.ENDER_PEARL.setMaxStackSize(64);
            Items.SNOWBALL.setMaxStackSize(64);
            Items.EGG.setMaxStackSize(64);
            Items.ENCHANTED_BOOK.setMaxStackSize(16);
            Items.TOTEM_OF_UNDYING.setMaxStackSize(16);
            Items.CAKE.setMaxStackSize(16);

            //Items.POTIONITEM.setMaxStackSize(16);
            //Items.SPLASH_POTION.setMaxStackSize(16);
            //Items.LINGERING_POTION.setMaxStackSize(16);
            //Items.BEETROOT_SOUP.setMaxStackSize(16);
            //Items.MUSHROOM_STEW.setMaxStackSize(16);
            //Items.RABBIT_STEW.setMaxStackSize(16);

            Items.BUCKET.setMaxStackSize(64);
            Items.WATER_BUCKET.setMaxStackSize(16);
            Items.LAVA_BUCKET.setMaxStackSize(16);

            if (Configs.undrinkableBuckets) Items.MILK_BUCKET.setMaxStackSize(16);

            //Tooltips
            //items.add(Items.BREWING_STAND);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void applyReplacementTooltips(ItemTooltipEvent event) {
        if (items.contains(event.getItemStack().getItem())) {
            event.getToolTip().add(ChatHelper.MAGENTA + "Replaced by Compressed Blocks");
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void applyTooltips(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() == Items.MILK_BUCKET && Configs.undrinkableBuckets) {
            event.getToolTip().add("Who even drinks a cubic meter of milk?");
            event.getToolTip().add("Pour it into bottles first!");
        }
    }

    private static void changeEntityLoot() {
        if (Configs.entityDrops) {

        }
    }

    public static boolean isValidInput(@Nonnull ItemStack stack) {
        for (IBrewingRecipe recipe : BrewingRecipeRegistry.getRecipes()){
            if (recipe.isInput(stack)) {
                LogHelper.info("true");
                return true;
            }
        }
        return false;
    }
}
