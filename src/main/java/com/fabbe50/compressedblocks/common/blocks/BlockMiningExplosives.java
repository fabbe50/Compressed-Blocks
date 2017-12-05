package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.utils.helper.ChatHelper;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 21/04/2017.
 */
public class BlockMiningExplosives extends BlockBase {
    public BlockMiningExplosives(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
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

            List<Block> ores = getOres();
            List<Block> collectedOres = new ArrayList<>();

            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 255; y++) {
                    for (int z = 0; z < 16; z++) {
                        if (worldIn.getBlockState(bPos).getBlock() != Blocks.BEDROCK && worldIn.getBlockState(bPos).getBlock() != BlockRegistry.MININGEXPLOSIVES) {
                            if (ores.contains(worldIn.getBlockState(bPos).getBlock())) {
                                collectedOres.add(worldIn.getBlockState(bPos).getBlock());
                                worldIn.setBlockState(bPos, Blocks.AIR.getDefaultState());
                                worldIn.setBlockState(bPos, Blocks.STONE.getDefaultState());
                            }
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

            List<Block> oresAfterChested = new ArrayList<>();
            BlockPos chestPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            ItemStack stack = null;
            worldIn.setBlockState(chestPos, Configs.parseChest().getDefaultState());

            for (Block ore : collectedOres) {
                try {
                    if (worldIn.getBlockState(chestPos).getBlock() instanceof BlockChest) {
                        IInventory inventory = getInventoryAtPosition(worldIn, pos.getX(), pos.getY() + 1, pos.getZ());
                        stack = putItemInInventory(inventory, new ItemStack(ore));
                        oresAfterChested.add(Block.getBlockFromItem(stack.getItem()));
                    } else {
                        EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY() + 2, pos.getZ(), new ItemStack(ore));
                        worldIn.spawnEntity(item);
                    }
                } catch (Exception e) {
                    LogHelper.error(e + ": Couldn't fill inventory.");
                    EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY() + 2, pos.getZ(), new ItemStack(ore));
                    worldIn.spawnEntity(item);
                }
            }

            for (Block ore : oresAfterChested) {
                EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY() + 2, pos.getZ(), new ItemStack(ore));
                worldIn.spawnEntity(item);
            }

            worldIn.setBlockState(pos, Blocks.GLASS.getDefaultState());
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

    private List<Block> getOres() {
        List<Block> ores = new ArrayList<>();

        ores.add(Blocks.COAL_ORE);
        ores.add(Blocks.IRON_ORE);
        ores.add(Blocks.GOLD_ORE);
        ores.add(Blocks.REDSTONE_ORE);
        ores.add(Blocks.LIT_REDSTONE_ORE);
        ores.add(Blocks.LAPIS_ORE);
        ores.add(Blocks.DIAMOND_ORE);
        ores.add(Blocks.EMERALD_ORE);
        ores.add(Blocks.QUARTZ_ORE);
        ores.add(BlockRegistry.FUSEROCK);
        ores.add(BlockRegistry.FUSEROCKNETHER);
        ores.add(BlockRegistry.FUSEROCKEND);

        return ores;
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
                if (iinventory instanceof TileEntityChest && block instanceof BlockChest) {
                    iinventory = ((BlockChest)block).getContainer(worldIn, blockpos, true);
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

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag flags) {
        tooltip.add(ChatHelper.MAGENTA + "[Experimental]");
        tooltip.add("");
        tooltip.add("Clears the ores in the chunk it's in and deposits all ores in a chest.");
        tooltip.add("If chest gets filled up for whatever reason, the rest of the stuff will drop on the ground.");
    }
}
