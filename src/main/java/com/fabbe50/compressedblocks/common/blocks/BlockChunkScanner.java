package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.tileentities.TileEntityChunkScanner;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 11/12/2017 - 12:19 PM.
 */
public class BlockChunkScanner extends BlockContainer implements ITileEntityProvider {
    public BlockChunkScanner(Material materialIn) {
        super(materialIn);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityChunkScanner();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            int xx = (pos.getX() % 16);
            int zz = (pos.getZ() % 16);
            int x2;
            int z2;

            if (zz == 0 && pos.getZ() < 0)
                z2 = pos.getZ() - zz + 16;
            else
                z2 = pos.getZ() - zz;

            if (xx == 0 && pos.getX() < 0)
                x2 = pos.getX() - xx + 16;
            else
                x2 = pos.getX() - xx;

            if (z2 < 0)
                z2--;
            if (x2 < 0)
                x2--;

            BlockPos bPos = new BlockPos(x2, 0, z2);

            List<Block> blocks = new ArrayList<>();

            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 255; y++) {
                    for (int z = 0; z < 16; z++) {
                        if (worldIn.getBlockState(bPos).getBlock() != Blocks.BEDROCK && worldIn.getBlockState(bPos).getBlock() != BlockRegistry.CHUNK_SCANNER) {
                            blocks.add(worldIn.getBlockState(bPos).getBlock());
                        }
                        if (x2 > 0 && z2 > 0)
                            bPos = new BlockPos(x2 + x, y, z2 + z);
                        else if (x2 > 0 && z2 < 0)
                            bPos = new BlockPos(x2 + x, y, z2 - z);
                        else if (x2 < 0 && z2 > 0)
                            bPos = new BlockPos(x2 - x, y, z2 + z);
                        else if (x2 < 0 && z2 < 0)
                            bPos = new BlockPos(x2 - x, y, z2 - z);
                    }
                }
            }

            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity != null) {
                if (!((IInventory) tileentity).isEmpty())
                    ((IInventory) tileentity).clear();
                playerIn.displayGUIChest((IInventory) tileentity);
            }

            List<Block> oresAfterChested = new ArrayList<>();
            ItemStack stack = null;

            for (Block ore : blocks) {
                try {
                    if (worldIn.getBlockState(pos).getBlock() instanceof BlockChunkScanner) {
                        IInventory inventory = getInventoryAtPosition(worldIn, pos.getX(), pos.getY(), pos.getZ());
                        stack = putItemInInventory(inventory, new ItemStack(ore));
                        oresAfterChested.add(Block.getBlockFromItem(stack.getItem()));
                    } else {
                        EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ore));
                        item.addVelocity(-item.motionX, -item.motionY, -item.motionZ);
                        worldIn.spawnEntity(item);
                    }
                } catch (Exception e) {
                    LogHelper.error(e + ": Couldn't fill inventory.");
                    EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(ore));
                    item.addVelocity(-item.motionX, -item.motionY, -item.motionZ);
                    worldIn.spawnEntity(item);
                }
            }

            return true;
        }
        return false;
    }

    private ItemStack putItemInInventory(IInventory inventory, ItemStack itemStack) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            if (canInsertItemInSlot(inventory, itemStack, i, EnumFacing.UP)) {
                ItemStack stack = inventory.getStackInSlot(i);

                if (stack.isEmpty()) {
                    inventory.setInventorySlotContents(i, itemStack);
                    itemStack = ItemStack.EMPTY;
                }
                else if (canCombine(stack, itemStack)) {
                    int j = itemStack.getMaxStackSize() - stack.getCount();
                    int k = Math.min(itemStack.getCount(), j);
                    itemStack.shrink(k);
                    stack.grow(k);
                }
            }
        }

        return itemStack;
    }

    public static IInventory getInventoryAtPosition(World worldIn, double x, double y, double z) {
        IInventory iinventory = null;
        int i = MathHelper.floor(x);
        int j = MathHelper.floor(y);
        int k = MathHelper.floor(z);
        BlockPos blockpos = new BlockPos(i, j, k);
        net.minecraft.block.state.IBlockState state = worldIn.getBlockState(blockpos);
        Block block = state.getBlock();

        if (block.hasTileEntity(state)) {
            TileEntity tileentity = worldIn.getTileEntity(blockpos);
            if (tileentity instanceof IInventory) {
                iinventory = (IInventory)tileentity;
                if (iinventory instanceof TileEntityChunkScanner && block instanceof BlockChunkScanner) {
                    iinventory = ((BlockChunkScanner)block).getContainer(worldIn, blockpos, true);
                }
            }
        }
        if (iinventory == null) {
            List<Entity> list = worldIn.getEntitiesInAABBexcluding((Entity)null, new AxisAlignedBB(x - 0.5D, y - 0.5D, z - 0.5D, x + 0.5D, y + 0.5D, z + 0.5D), EntitySelectors.HAS_INVENTORY);
            if (!list.isEmpty()) {
                iinventory = (IInventory)list.get(worldIn.rand.nextInt(list.size()));
            }
        }

        return iinventory;
    }

    private static boolean canCombine(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && (stack1.getMetadata() == stack2.getMetadata() && (stack1.getCount() <= stack1.getMaxStackSize() && ItemStack.areItemStackTagsEqual(stack1, stack2)));
    }

    private static boolean canInsertItemInSlot(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side) {
        return inventoryIn.isItemValidForSlot(index, stack) && (!(inventoryIn instanceof ISidedInventory) || ((ISidedInventory) inventoryIn).canInsertItem(index, stack, side));
    }

    @Nullable
    public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityChunkScanner)) {
            return null;
        } else return (TileEntityChunkScanner) tileentity;
    }
}
