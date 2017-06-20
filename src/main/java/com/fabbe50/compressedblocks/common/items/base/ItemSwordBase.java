package com.fabbe50.compressedblocks.common.items.base;

import com.fabbe50.compressedblocks.core.registry.ToolMaterialRegistry;
import com.thefifthidiot.tficore.common.creativetabs.TFITab;
import com.thefifthidiot.tficore.lib.Configs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 02/06/2017.
 */
public class ItemSwordBase extends ItemSword {
    private final float attackDamage;
    private final Item.ToolMaterial material;

    public ItemSwordBase(ToolMaterial material, String name, @Nullable CreativeTabs tab) {
        super(material);
        this.material = material;
        this.maxStackSize = 1;
        setItemName(this, name);
        this.attackDamage = material.getDamageVsEntity();
        setCreativeTab(tab != null ? tab : (Configs.tfitabs ? TFITab.blockTab : null));
    }

    private static void setItemName(Item item, String itemName) {
        item.setRegistryName(itemName);
        item.setUnlocalizedName(item.getRegistryName().toString());
    }

    @Override
    public float getDamageVsEntity() {
        return this.material.getDamageVsEntity();
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
