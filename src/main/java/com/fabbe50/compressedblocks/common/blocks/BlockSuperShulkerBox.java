package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.common.tileentities.TileEntitySuperShulkerBox;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe50 on 19/02/2017.
 */
@SuppressWarnings("deprecation")
public class BlockSuperShulkerBox extends BlockContainer implements ITileEntityProvider {
    public static final PropertyEnum<EnumFacing> FACING = PropertyDirection.create("facing");
    private final EnumDyeColor color;

    public BlockSuperShulkerBox(EnumDyeColor colorIn, Material material, MapColor mapColor, String name, float hardness, float resistance, CreativeTabs tab) {
        super(Material.ROCK, MapColor.AIR);
        setBlockName(this, name);
        this.color = colorIn;
        this.setCreativeTab(tab != null ? tab : CBTab.blockTab);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
    }

    public static void setBlockName(Block block, String blockName) {
        block.setRegistryName(blockName);
        block.setTranslationKey(block.getRegistryName().toString());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntitySuperShulkerBox(this.color);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean causesSuffocation(IBlockState state) {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasCustomBreakingProgress(IBlockState state) {
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        }
        else if (playerIn.isSpectator()) {
            return true;
        }
        else {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntitySuperShulkerBox) {
                EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
                boolean flag;

                if (((TileEntitySuperShulkerBox)tileentity).getAnimationStatus() == TileEntitySuperShulkerBox.AnimationStatus.CLOSED) {
                    AxisAlignedBB axisalignedbb = FULL_BLOCK_AABB.expand((double)(0.5F * (float)enumfacing.getXOffset()), (double)(0.5F * (float)enumfacing.getYOffset()), (double)(0.5F * (float)enumfacing.getZOffset())).contract((double)enumfacing.getXOffset(), (double)enumfacing.getYOffset(), (double)enumfacing.getZOffset());
                    flag = !worldIn.collidesWithAnyBlock(axisalignedbb.offset(pos.offset(enumfacing)));
                }
                else {
                    flag = true;
                }

                if (flag) {
                    playerIn.addStat(StatList.OPEN_SHULKER_BOX);
                    playerIn.displayGUIChest((IInventory)tileentity);
                }

                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.byIndex(meta);
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (worldIn.getTileEntity(pos) instanceof TileEntitySuperShulkerBox) {
            TileEntitySuperShulkerBox tileentitysupershulkerbox = (TileEntitySuperShulkerBox) worldIn.getTileEntity(pos);
            tileentitysupershulkerbox.setDestroyedByCreativePlayer(player.capabilities.isCreativeMode);
            tileentitysupershulkerbox.fillWithLoot(player);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntitySuperShulkerBox) {
                ((TileEntitySuperShulkerBox)tileentity).setCustomName(stack.getDisplayName());
            }
        }
        if (stack.hasTagCompound()) {
            TileEntity box = worldIn.getTileEntity(pos);
            if (stack.hasTagCompound()) {
                NBTTagCompound tag = stack.getTagCompound();
                box.writeToNBT(tag);
                if (stack.hasDisplayName()) {
                    tag.setString("name", stack.getDisplayName());
                }
                box.readFromNBT(tag);
                box.validate();
                worldIn.setTileEntity(pos, box);
            }
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileEntitySuperShulkerBox) {
            TileEntitySuperShulkerBox tileentityshulkerbox = (TileEntitySuperShulkerBox)tileentity;

            if (!tileentityshulkerbox.isCleared() && tileentityshulkerbox.shouldDrop()) {
                ItemStack itemstack = new ItemStack(Item.getItemFromBlock(this));
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound.setTag("BlockEntityTag", ((TileEntitySuperShulkerBox)tileentity).saveToNbt(nbttagcompound1));
                itemstack.setTagCompound(nbttagcompound);

                if (tileentityshulkerbox.hasCustomName()) {
                    itemstack.setStackDisplayName(tileentityshulkerbox.getName());
                    tileentityshulkerbox.setCustomName("");
                }
                spawnAsEntity(worldIn, pos, itemstack);
            }
            worldIn.updateComparatorOutputLevel(pos, state.getBlock());
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null && nbttagcompound.hasKey("BlockEntityTag", 10)) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("BlockEntityTag");

            if (nbttagcompound1.hasKey("LootTable", 8)) {
                tooltip.add("???????");
            }

            if (nbttagcompound1.hasKey("Items", 9)) {
                NonNullList<ItemStack> nonnulllist = NonNullList.<ItemStack>withSize(54, ItemStack.EMPTY);
                ItemStackHelper.loadAllItems(nbttagcompound1, nonnulllist);
                int i = 0;
                int j = 0;

                for (ItemStack itemstack : nonnulllist) {
                    if (!itemstack.isEmpty()) {
                        ++j;

                        if (i <= 4) {
                            ++i;
                            tooltip.add(String.format("%s x%d", new Object[] {itemstack.getDisplayName(), Integer.valueOf(itemstack.getCount())}));
                        }
                    }
                }

                if (j - i > 0) {
                    tooltip.add(String.format(TextFormatting.ITALIC + I18n.translateToLocal("container.shulkerBox.more"), new Object[] {Integer.valueOf(j - i)}));
                }
            }
        }
    }

    @Override
    public EnumPushReaction getPushReaction(IBlockState state) {
        return EnumPushReaction.DESTROY;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        TileEntity tileentity = source.getTileEntity(pos);
        return tileentity instanceof TileEntitySuperShulkerBox ? ((TileEntitySuperShulkerBox)tileentity).getBoundingBox(state) : FULL_BLOCK_AABB;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstoneFromInventory((IInventory)worldIn.getTileEntity(pos));
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        ItemStack itemstack = super.getItem(worldIn, pos, state);
        TileEntitySuperShulkerBox tileentityshulkerbox = (TileEntitySuperShulkerBox)worldIn.getTileEntity(pos);
        NBTTagCompound nbttagcompound = tileentityshulkerbox.saveToNbt(new NBTTagCompound());

        if (!nbttagcompound.isEmpty()) {
            itemstack.setTagInfo("BlockEntityTag", nbttagcompound);
        }

        return itemstack;
    }

    public static Block getBlockByColor(EnumDyeColor colorIn) {
        switch (colorIn) {
            case WHITE:
                return BlockRegistry.WHITE_SHULKER_BOX;
            case ORANGE:
                return BlockRegistry.ORANGE_SHULKER_BOX;
            case MAGENTA:
                return BlockRegistry.MAGENTA_SHULKER_BOX;
            case LIGHT_BLUE:
                return BlockRegistry.LIGHT_BLUE_SHULKER_BOX;
            case YELLOW:
                return BlockRegistry.YELLOW_SHULKER_BOX;
            case LIME:
                return BlockRegistry.LIME_SHULKER_BOX;
            case PINK:
                return BlockRegistry.PINK_SHULKER_BOX;
            case GRAY:
                return BlockRegistry.GRAY_SHULKER_BOX;
            case SILVER:
                return BlockRegistry.SILVER_SHULKER_BOX;
            case CYAN:
                return BlockRegistry.CYAN_SHULKER_BOX;
            case PURPLE:
            default:
                return BlockRegistry.PURPLE_SHULKER_BOX;
            case BLUE:
                return BlockRegistry.BLUE_SHULKER_BOX;
            case BROWN:
                return BlockRegistry.BROWN_SHULKER_BOX;
            case GREEN:
                return BlockRegistry.GREEN_SHULKER_BOX;
            case RED:
                return BlockRegistry.RED_SHULKER_BOX;
            case BLACK:
                return BlockRegistry.BLACK_SHULKER_BOX;
        }
    }

    @SideOnly(Side.CLIENT)
    public static EnumDyeColor getColorFromItem(Item itemIn) {
        return getColorFromBlock(Block.getBlockFromItem(itemIn));
    }

    public static ItemStack getColoredItemStack(EnumDyeColor colorIn) {
        return new ItemStack(getBlockByColor(colorIn));
    }

    @SideOnly(Side.CLIENT)
    public static EnumDyeColor getColorFromBlock(Block blockIn) {
        return blockIn instanceof BlockSuperShulkerBox ? ((BlockSuperShulkerBox)blockIn).getColor() : EnumDyeColor.PURPLE;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        state = this.getActualState(state, worldIn, pos);
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        TileEntitySuperShulkerBox.AnimationStatus TileEntitySuperShulkerBox$animationstatus = ((TileEntitySuperShulkerBox)worldIn.getTileEntity(pos)).getAnimationStatus();
        return TileEntitySuperShulkerBox$animationstatus != TileEntitySuperShulkerBox.AnimationStatus.CLOSED && (TileEntitySuperShulkerBox$animationstatus != TileEntitySuperShulkerBox.AnimationStatus.OPENED || enumfacing != face.getOpposite() && enumfacing != face) ? BlockFaceShape.UNDEFINED : BlockFaceShape.SOLID;
    }

    @SideOnly(Side.CLIENT)
    public EnumDyeColor getColor() {
        return this.color;
    }
}
