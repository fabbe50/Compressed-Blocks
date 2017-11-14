package com.fabbe50.compressedblocks.common.items;

import com.thefifthidiot.tficore.common.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

/**
 * Created by fabbe on 22/10/2017 - 10:59 AM.
 */
public class ItemTrinket extends ItemBase {
    public ItemTrinket(String itemName, CreativeTabs tab) {
        super(itemName, tab);
        this.setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
        subItems.add(new ItemStack(itemIn, 1, 2));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        switch (stack.getMetadata()) {
            case 0:
                tooltip.add("Counters the Ender Apples quirks.");
                break;
            case 1:
                tooltip.add("Prevents Ender teleportation.");
                break;
            case 2:
                tooltip.add("Disables dimensional teleportation.");
                break;
        }
    }
}
