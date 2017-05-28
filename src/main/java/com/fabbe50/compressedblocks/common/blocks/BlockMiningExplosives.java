package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.thefifthidiot.tficore.common.blocks.FuseRock;
import com.thefifthidiot.tficore.common.blocks.base.BlockBase;
import com.thefifthidiot.tficore.utility.helper.ChatHelper;
import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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
            List<Block> blocks = new ArrayList<>();
            int xx = pos.getX() % 16;
            int zz = pos.getZ() % 16;
            int x2 = pos.getX() - xx;
            int z2 = 0;
            if (pos.getZ() % 16 == 0) {
                z2 = pos.getZ() - zz;
            } else {
                z2 = pos.getZ() - zz - 16;
            }

            BlockPos bPos = new BlockPos(x2, 0, z2);

            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 255; y++) {
                    for (int z = 0; z < 16; z++) {
                        blocks.add(worldIn.getBlockState(bPos).getBlock());
                        if (worldIn.getBlockState(bPos).getBlock() != Blocks.BEDROCK && worldIn.getBlockState(bPos).getBlock() != BlockRegistry.MININGEXPLOSIVES) {
                            worldIn.setBlockState(bPos, Blocks.AIR.getDefaultState());
                        }
                        bPos = new BlockPos(x2 + x, y, z2 + z);
                    }
                }
            }
            List<Block> ores = getOres(worldIn, blocks);
            List<Block> oresAfterChested = new ArrayList<>();
            BlockPos chestPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
            ItemStack stack = null;
            worldIn.setBlockState(chestPos, Configs.parseChest().getDefaultState());

            for (int x3 = -1; x3 < 2; x3++) {
                for (int z3 = -1; z3 < 2; z3++) {
                    worldIn.setBlockState(new BlockPos(pos.getX() + x3, pos.getY() - 1, pos.getZ() + z3), Blocks.GRASS.getDefaultState());
                }
            }

            for (Block ore : ores) {
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

    private List<Block> getOres(World world, List<Block> blocks) {
        List<Block> ores = new ArrayList<>();

        for (Block block : blocks) {
            if (block == Blocks.IRON_ORE) {
                ores.add(Blocks.IRON_ORE);
            } else if (block == Blocks.GOLD_ORE) {
                ores.add(Blocks.GOLD_ORE);
            } else if (block == Blocks.DIAMOND_ORE) {
                ores.add(Blocks.DIAMOND_ORE);
            } else if (block == Blocks.LAPIS_ORE) {
                ores.add(Blocks.LAPIS_ORE);
            } else if (block == Blocks.EMERALD_ORE) {
                ores.add(Blocks.EMERALD_ORE);
            } else if (block == Blocks.COAL_ORE) {
                ores.add(Blocks.COAL_ORE);
            } else if (block instanceof FuseRock) {
                boolean overworld = false;
                switch (world.provider.getDimension()) {
                    case -1:
                        ores.add(com.thefifthidiot.tficore.core.registry.BlockRegistry.fuseRockNether);
                    case 0:
                        ores.add(com.thefifthidiot.tficore.core.registry.BlockRegistry.fuseRock);
                        overworld = true;
                    case 1:
                        if (!overworld) ores.add(com.thefifthidiot.tficore.core.registry.BlockRegistry.fuseRockEnd);
                }
            } else if (OreDictionary.getOres("oreCopper").contains(new ItemStack(Item.getItemFromBlock(block)))) {
                ores.add(Block.getBlockFromItem(OreDictionary.getOres("oreCopper").get(0).getItem()));
            } else if (OreDictionary.getOres("oreTin").contains(new ItemStack(Item.getItemFromBlock(block)))) {
                ores.add(Block.getBlockFromItem(OreDictionary.getOres("oreTin").get(0).getItem()));
            } else if (OreDictionary.getOres("oreSilver").contains(new ItemStack(Item.getItemFromBlock(block)))) {
                ores.add(Block.getBlockFromItem(OreDictionary.getOres("oreSilver").get(0).getItem()));
            }
        }
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
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        tooltip.add(ChatHelper.MAGENTA + "[Experimental]");
        tooltip.add("");
        tooltip.add(ChatHelper.LIME + "Stay close if you can't fly!");
        tooltip.add("");
        tooltip.add("Clear the chunk it's in and deposits all ores in a chest.");
        tooltip.add("If chest gets filled up for whatever reason, the rest of the stuff will drop on the ground.");
    }
}
