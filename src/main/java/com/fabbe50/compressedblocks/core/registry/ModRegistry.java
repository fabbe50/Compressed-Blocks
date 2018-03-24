package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.lib.recipes.EndgameRecipes;
import com.fabbe50.compressedblocks.core.lib.recipes.RecipeReturnItem;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.GameData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by fabbe on 09/05/2017.
 */
public class ModRegistry {
    public static List<EndgameRecipes> endgameRecipes = new ArrayList<>();

    public static void addEndgameRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.newArrayList();

        for (Object object : recipeComponents) {
            if (object instanceof ItemStack) {
                list.add(((ItemStack)object).copy());
            } else if (object instanceof Item) {
                list.add(new ItemStack((Item)object));
            } else {
                if (!(object instanceof Block)) {
                    throw new IllegalArgumentException("Invalid endgame recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(new ItemStack((Block)object));
            }
        }

        endgameRecipes.add(new EndgameRecipes(stack, list));
    }

    public static void addReturnRecipe(ItemStack stack, Object... recipeComponents) {
        List<ItemStack> list = Lists.newArrayList();

        for (Object object : recipeComponents) {
            if (object instanceof ItemStack) {
                list.add(((ItemStack)object).copy());
            } else if (object instanceof Item) {
                list.add(new ItemStack((Item)object));
            } else {
                if (!(object instanceof Block)) {
                    throw new IllegalArgumentException("Invalid return recipe: unknown type " + object.getClass().getName() + "!");
                }

                list.add(new ItemStack((Block)object));
            }
        }

        GameData.register_impl(new RecipeReturnItem(stack, list).setRegistryName(Reference.MOD_ID + ":recipeReturn." + Objects.requireNonNull(stack.getItem().getRegistryName()).getResourcePath()));
    }
}
