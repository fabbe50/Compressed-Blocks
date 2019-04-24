package com.fabbe50.compressedblocks.development.common.block;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.development.common.container.ContainerWorkbenchTest;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

/**
 * Created by fabbe on 02/02/2018 - 1:41 AM.
 */
public class BlockWorkbenchTest extends BlockWorkbench {
    public BlockWorkbenchTest() {
        this.setRegistryName(Reference.MOD_ID, "test_workbench");
        this.setTranslationKey(this.getRegistryName().toString());
        this.setCreativeTab(CBTab.blockTab);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) {
            return true;
        } else {
            playerIn.displayGui(new BlockWorkbenchTest.InterfaceCraftingTableTest(worldIn, pos));
            playerIn.addStat(StatList.CRAFTING_TABLE_INTERACTION);
            return true;
        }
    }

    public static class InterfaceCraftingTableTest extends InterfaceCraftingTable implements IInteractionObject {
        private final World world;
        private final BlockPos position;

        public InterfaceCraftingTableTest(World worldIn, BlockPos pos) {
            super(worldIn, pos);
            this.world = worldIn;
            this.position = pos;
        }

        @Override
        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return new ContainerWorkbenchTest(playerInventory, this.world, this.position);
        }
    }
}
