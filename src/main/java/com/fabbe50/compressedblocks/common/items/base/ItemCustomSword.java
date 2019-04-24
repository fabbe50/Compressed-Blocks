package com.fabbe50.compressedblocks.common.items.base;

import com.fabbe50.compressedblocks.core.registry.ToolMaterialRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 09/01/2018 - 11:13 AM.
 */
public class ItemCustomSword extends ItemTool {
    private final float damage;
    private final Item.ToolMaterial material;
    private final boolean isEnchanted;

    public ItemCustomSword(ToolMaterial material, float durabilityModifier, float damageModifier, float attackSpeed, boolean isEnchanted, String name, CreativeTabs tab) {
        super((material.getAttackDamage() * damageModifier), (attackSpeed - 4), material, null);
        this.material = material;
        this.isEnchanted = isEnchanted;
        this.setMaxDamage(Math.round(material.getMaxUses() * durabilityModifier));
        this.damage = 3.0f + (material.getAttackDamage() * damageModifier);
        this.setItemName(name);
        this.setCreativeTab(tab);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return isEnchanted || material.equals(ToolMaterialRegistry.TOOL_ENDGAMIUM);
    }

    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player) {
        return false;
    }

    private void setItemName(String itemName) {
        this.setRegistryName(itemName);
        this.setTranslationKey(this.getRegistryName().toString());
    }
}
