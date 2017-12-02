package com.fabbe50.compressedblocks.common.inventory;

import com.google.common.base.Preconditions;
import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

import javax.annotation.Nonnull;

/**
 * Created by fabbe on 02/12/2017 - 2:26 AM.
 */
@ChestContainer
public class ContainerSingularityPickaxe extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
