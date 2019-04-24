package com.fabbe50.compressedblocks.plugin.jei;

import com.fabbe50.compressedblocks.core.reference.Reference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import mezz.jei.config.Constants;
import mezz.jei.util.Translator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class BeaconCraftingCategory extends BlankRecipeCategory<IRecipeWrapper> {
    private static final int craftOutputSlot = 0;
    private static final int craftInputSlot1 = 1;
    public static final int width = 116;
    public static final int height = 54;
    private final IDrawable background;
    private final String localizedName;
    private final ICraftingGridHelper craftingGridHelper;

    public BeaconCraftingCategory(IGuiHelper guiHelper) {
        ResourceLocation location = Constants.RECIPE_GUI_VANILLA;
        this.background = guiHelper.createDrawable(location, 0, 60, 116, 54);
        this.localizedName = Translator.translateToLocal("gui.jei.category.beaconCrafting");
        this.craftingGridHelper = guiHelper.createCraftingGridHelper(1, 0);
    }

    @Override
    public String getUid() {
        return "compressedblocks.beaconcrafting";
    }

    @Override
    public String getTitle() {
        return "Crafting";
    }

    @Override
    public String getModName() {
        return Reference.MOD_NAME;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, IRecipeWrapper iRecipeWrapper, IIngredients iIngredients) {
        IGuiItemStackGroup guiItemStacks = iRecipeLayout.getItemStacks();
        guiItemStacks.init(0, false, 94, 18);

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                int index = 1 + x + y * 3;
                guiItemStacks.init(index, true, x * 18, y * 18);
            }
        }

        if (iRecipeWrapper instanceof ICustomCraftingRecipeWrapper) {
            ICustomCraftingRecipeWrapper customWrapper = (ICustomCraftingRecipeWrapper) iRecipeWrapper;
            customWrapper.setRecipe(iRecipeLayout, iIngredients);
        } else {
            List<List<ItemStack>> inputs = iIngredients.getInputs(ItemStack.class);
            List<List<ItemStack>> outputs = iIngredients.getOutputs(ItemStack.class);
            if (iRecipeWrapper instanceof IShapedCraftingRecipeWrapper) {
                IShapedCraftingRecipeWrapper wrapper = (IShapedCraftingRecipeWrapper) iRecipeWrapper;
                this.craftingGridHelper.setInputs(guiItemStacks, inputs, wrapper.getWidth(), wrapper.getHeight());
            } else {
                this.craftingGridHelper.setInputs(guiItemStacks, inputs);
            }

            guiItemStacks.set(0, (List) outputs.get(0));
        }
    }
}
