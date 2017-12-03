package com.fabbe50.compressedblocks.common.items.base;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.core.registry.ToolMaterialRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 02/06/2017.
 */
public class ItemSwordBase extends ItemTool {
    //private final float attackDamage;
    private final Item.ToolMaterial material;

    public ItemSwordBase(ToolMaterial material, String name, @Nullable CreativeTabs tab) {
        super(material.getAttackDamage(), 10, material, null);
        this.material = material;
        this.maxStackSize = 1;
        setItemName(this, name);
        //this.attackDamage = material.getDamageVsEntity();
        setCreativeTab(tab != null ? tab : CBTab.itemTab);
    }

    private static void setItemName(Item item, String itemName) {
        item.setRegistryName(itemName);
        item.setUnlocalizedName(item.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        if (stack.getItem() instanceof ItemSwordBase) {
            if (((ItemSwordBase) stack.getItem()).material == ToolMaterialRegistry.TOOL_ENDGAMIUM) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return false;
    }
}
