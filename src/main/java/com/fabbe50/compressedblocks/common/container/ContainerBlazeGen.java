package com.fabbe50.compressedblocks.common.container;

import com.fabbe50.compressedblocks.common.tileentities.TileEntityBlazeGen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by fabbe50 on 20/04/2016.
 */
public class ContainerBlazeGen extends Container {
    private TileEntityBlazeGen blazeGen;

    public ContainerBlazeGen (InventoryPlayer inventoryPlayer, TileEntityBlazeGen blazeGen) {
        this.blazeGen = blazeGen;
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
}
