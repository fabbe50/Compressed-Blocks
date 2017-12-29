package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.core.lib.recipes.EndgameRecipes;
import com.fabbe50.compressedblocks.core.registry.ModRegistry;
import com.fabbe50.compressedblocks.core.utils.helper.CraftingHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by fabbe on 27/05/2017.
 */
public class BlockFusionPedestal extends BlockBase {
    private AxisAlignedBB bounds = new AxisAlignedBB(0, 0, 0, 1, 0.75, 1);

    public BlockFusionPedestal(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return bounds;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return doCrafting(worldIn, pos);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos)) {
                doCrafting(worldIn, pos);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!worldIn.isRemote) {
            if (worldIn.isBlockPowered(pos)) {
                doCrafting(worldIn, pos);
            }
        }
    }

    private boolean doCrafting(World worldIn, BlockPos pos) {
        if (!worldIn.isRemote) {
            AxisAlignedBB checkingBounds = new AxisAlignedBB(pos.getX(), pos.getY() + 0.75, pos.getZ(), pos.getX() + 0.9375, pos.getY() + 1.5, pos.getZ() + 0.9375);
            if (!worldIn.getEntitiesWithinAABB(EntityItem.class, checkingBounds).isEmpty()) {
                List<EntityItem> entities = worldIn.getEntitiesWithinAABB(EntityItem.class, checkingBounds);
                List<ItemStack> items = entities.stream().map(EntityItem::getItem).collect(Collectors.toList());

                for(EndgameRecipes recipes : ModRegistry.endgameRecipes) {
                    if (CraftingHelper.compareItemsFromListedStacks(items, recipes.recipeItems) || Block.getBlockFromItem(recipes.getRecipeOutput().getItem()) instanceof BlockSuperShulkerBox) {
                        if (Block.getBlockFromItem(recipes.getRecipeOutput().getItem()) instanceof BlockSuperShulkerBox) {
                            EntityItem outputItem = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                            ItemStack output = recipes.getRecipeOutput();
                            NBTTagCompound compound = items.get(0).getTagCompound();
                            output.setTagCompound(compound);
                            outputItem.setItem(output);
                            //outputItem.setVelocity(0, 0, 0);
                            outputItem.addVelocity(-outputItem.motionX, -outputItem.motionY, -outputItem.motionZ);
                            worldIn.spawnEntity(outputItem);
                            doEffects(worldIn, pos);
                            entities.forEach(EntityItem::setDead);
                            return true;
                        } else {
                            EntityItem outputItem = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                            outputItem.setItem(recipes.getRecipeOutput());
                            //outputItem.setVelocity(0, 0, 0);
                            outputItem.addVelocity(-outputItem.motionX, -outputItem.motionY, -outputItem.motionZ);
                            worldIn.spawnEntity(outputItem);
                            doEffects(worldIn, pos);
                            entities.forEach(EntityItem::setDead);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void doEffects(World worldIn, BlockPos pos) {
        worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), true));
        worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), true));
        worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), true));
        worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.getX(), pos.getY(), pos.getZ(), true));
        if (worldIn.isRemote) {
            worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, pos.getX(), pos.getY() + 1, pos.getZ(), 0, 0, 0);
        }
    }
}
