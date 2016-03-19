package com.fabbe50.compressedblocks.item.tools;

import com.fabbe50.compressedblocks.init.ModBlocks;
import com.fabbe50.compressedblocks.item.ItemCB;
import com.fabbe50.compressedblocks.lib.EnumModToolMaterial;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;
import java.util.Set;

/**
 * Created by fabbe50 on 15/03/2016.
 */
public class ItemModMultitool extends ItemCB {
    private static final Set fieldOfBlocks = Sets.newHashSet(Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone,
            Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore,
            Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail, Blocks.grass, Blocks.dirt,
            Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium, ModBlocks.comprstarblock, ModBlocks.comprpotatoblock,
            ModBlocks.endgamiumblock, ModBlocks.endgamiumblockc, Blocks.hardened_clay);
    EnumModToolMaterial toolMaterial;
    float efficiencyOnProperMaterial = 4.0F;
    float damage;
    EntityPlayer player;
    World world;
    Random rand = new Random();

    public ItemModMultitool (EnumModToolMaterial material) {
        super();
        this.maxStackSize = 1;
        this.damage = material.getDamageVsEntity();
        this.setMaxDamage(material.getMaxUses());
        this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        toolMaterial = material;
        setUnlocalizedName(material.name() + "tool");
    }

    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

    public boolean getIsRepairable(ItemStack p_82789_1_, ItemStack p_82789_2_)
    {
        ItemStack mat = toolMaterial.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, p_82789_2_, false)) return true;
        return super.getIsRepairable(p_82789_1_, p_82789_2_);
    }

    public Multimap getItemAttributeModifiers()
    {
        Multimap multimap = super.getItemAttributeModifiers();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.damage, 0));
        return multimap;
    }

    public boolean func_150897_b(Block p_150897_1_)
    {
        return fieldOfBlocks.contains(p_150897_1_) ? true : super.func_150897_b(p_150897_1_);
    }

    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_)
    {
        return p_150893_2_.getMaterial() != Material.sand && p_150893_2_.getMaterial() != Material.clay && p_150893_2_.getMaterial() != Material.grass && p_150893_2_.getMaterial() != Material.iron && p_150893_2_.getMaterial() != Material.anvil && p_150893_2_.getMaterial() != Material.rock ? super.func_150893_a(p_150893_1_, p_150893_2_) : this.efficiencyOnProperMaterial;
    }

    /*@Override
    public Set<String> getToolClasses(ItemStack stack)
    {
        return  != null ? ImmutableSet.of() : super.getToolClasses(stack);
    }*/

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta)
    {
        if (ForgeHooks.isToolEffective(stack, block, meta))
        {
            return efficiencyOnProperMaterial;
        }
        return super.getDigSpeed(stack, block, meta);
    }
}
