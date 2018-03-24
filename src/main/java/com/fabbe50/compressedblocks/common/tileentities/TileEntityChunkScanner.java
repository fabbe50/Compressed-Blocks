package com.fabbe50.compressedblocks.common.tileentities;

import com.fabbe50.compressedblocks.common.inventory.InventoryChunkScanner;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

/**
 * Created by fabbe on 11/12/2017 - 1:27 PM.
 */
public class TileEntityChunkScanner extends TileEntityLockableLoot implements ITickable, ISidedInventory {
    private static final int[] SLOTS = new int[54];
    private NonNullList<ItemStack> items;

    public TileEntityChunkScanner() {
        this.items = NonNullList.withSize(54, ItemStack.EMPTY);
    }

    @Override
    public int getSizeInventory() {
        return 54;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {
    }

    @Override
    public void closeInventory(EntityPlayer player) {
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void update() {

    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new InventoryChunkScanner(playerInventory, this, playerIn);
    }

    @Override
    public String getGuiID() {
        return "minecraft:generic_54";
    }

    @Override
    public String getName() {
        return I18n.format("container.chunkScanner");
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return SLOTS;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return true;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }
}
