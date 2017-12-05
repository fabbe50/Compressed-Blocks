package com.fabbe50.compressedblocks.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotShulkerBox;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 19/02/2017.
 */
public class ContainerSuperShulkerBox extends Container{
    private final IInventory inventory;

    public ContainerSuperShulkerBox(InventoryPlayer inventoryPlayer, IInventory inventory, EntityPlayer entityPlayer) {
        this.inventory = inventory;
        inventory.openInventory(entityPlayer);
        int i = 3;
        int j = 9;

        for (int k = 0; k < 6; ++k) {
            for (int l = 0; l < 9; ++l) {
                this.addSlotToContainer(new SlotShulkerBox(inventory, l + k * 9, 8 + l * 18, 18 + k * 18));
            }
        }

        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.addSlotToContainer(new Slot(inventoryPlayer, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
            }
        }

        for (int j1 = 0; j1 < 9; ++j1) {
            this.addSlotToContainer(new Slot(inventoryPlayer, j1, 8 + j1 * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.inventory.getSizeInventory()) {
                if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            }
            else {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
        this.inventory.closeInventory(playerIn);
    }
}
