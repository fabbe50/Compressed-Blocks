package com.fabbe50.compressedblocks.development.common.container;

import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe on 02/02/2018 - 2:02 AM.
 */
public class ContainerWorkbenchTest extends ContainerWorkbench {
    private final World world;
    private final BlockPos pos;

    public ContainerWorkbenchTest(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
        super(playerInventory, worldIn, posIn);
        this.world = worldIn;
        this.pos = posIn;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        if (this.world.getBlockState(this.pos).getBlock() != BlockRegistry.WORKBENCH_TEST) {
            return false;
        } else {
            return playerIn.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }
}
