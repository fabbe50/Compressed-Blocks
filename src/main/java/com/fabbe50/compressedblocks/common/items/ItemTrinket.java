package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.utility.helper.ChatHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
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
    public boolean hasEffect(ItemStack stack) {
        return stack.getMetadata() == 4;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.isRemote && worldIn.getTotalWorldTime() % 80L == 0L) {
            if (entityIn instanceof EntityPlayer && stack.getMetadata() == 4) {
                for (PotionEffect e : getPotionFromItem(stack)) {
                    ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(e.getPotion(), 180, e.getAmplifier(), false, false));
                }
            }
        }
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        NBTTagCompound tags = stack.getTagCompound();

        if (tags == null)
            tags = new NBTTagCompound();

        stack.setTagCompound(tags);
    }

    public static List<PotionEffect> getPotionFromItem(ItemStack stack) {
        return PotionUtils.getEffectsFromStack(stack);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(this, 1, 0));
        subItems.add(new ItemStack(this, 1, 1));
        subItems.add(new ItemStack(this, 1, 2));
        subItems.add(new ItemStack(this, 1, 3));
        subItems.add(new ItemStack(this, 1, 4)); //Portable Beacon
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        switch (stack.getMetadata()) {
            case 0:
                tooltip.add("No effects");
                break;
            case 1:
                tooltip.add("Counters the Ender Apples quirks.");
                break;
            case 2:
                tooltip.add("Prevents Ender teleportation.");
                break;
            case 3:
                tooltip.add("Disables dimensional teleportation.");
                break;
            case 4:
                stack.setTranslatableName("item." + Reference.MOD_ID + ":minibeacon.name");
                if (!PotionUtils.getEffectsFromStack(stack).isEmpty())
                    tooltip.add(ChatHelper.MAGENTA + "Effects applied:");
                else {
                    tooltip.add(ChatHelper.MAGENTA + "No effects applied.");
                    tooltip.add(ChatHelper.PURPLE + "Start by combining this with");
                    tooltip.add(ChatHelper.PURPLE + "a beacon and a potion.");
                }
                for (PotionEffect e : PotionUtils.getEffectsFromStack(stack)) {
                    tooltip.add(ChatHelper.PURPLE + I18n.format(e.getEffectName()) + " " + (e.getAmplifier() + 1));
                }
                break;
        }
    }
}
