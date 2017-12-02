package com.fabbe50.compressedblocks.common.inventory.slots;

import com.fabbe50.compressedblocks.common.items.ItemSingularityPickaxe;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe on 02/12/2017 - 1:02 AM.
 */
public class SlotSingularity extends Slot {
    public SlotSingularity(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return !(stack.getItem() instanceof ItemSingularityPickaxe);
    }
}
