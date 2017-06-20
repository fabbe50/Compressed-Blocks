package com.fabbe50.compressedblocks.common.items;

import com.thefifthidiot.tficore.common.items.ItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 26/04/2017.
 */
public class ItemEnderApple extends ItemFood {
    public ItemEnderApple(int amount, float saturation, boolean isWolfFood, String registryName) {
        super(amount, saturation, isWolfFood, registryName);
        this.setHasSubtypes(true);
        this.setMaxStackSize(64);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getMetadata() != 0;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return stack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            if (stack.getMetadata() > 0) {
                player.changeDimension(1);

                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 2));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 2));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 3200, 4));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3600, 3));
            }
            else {
                BlockPos tpPos = new BlockPos(player.posX + getPlusMinusBound(40), player.posY + getPlusMinusBound(20), player.posZ + getPlusMinusBound(40));

                if (tpPos.getY() < 1)
                    tpPos = new BlockPos(tpPos.getX(), 1, tpPos.getZ());

                EntityItem item = new EntityItem(worldIn, tpPos.getX(), tpPos.getY(), tpPos.getZ(), new ItemStack(worldIn.getBlockState(tpPos).getBlock()));
                worldIn.setBlockState(tpPos, Blocks.AIR.getDefaultState());
                EntityItem item2 = new EntityItem(worldIn, tpPos.getX(), tpPos.getY() + 1, tpPos.getZ(), new ItemStack(worldIn.getBlockState(tpPos.add(0, 1, 0)).getBlock()));
                worldIn.setBlockState(tpPos.add(0, 1, 0), Blocks.AIR.getDefaultState());
                worldIn.spawnEntity(item);
                worldIn.spawnEntity(item2);
                player.attemptTeleport(tpPos.getX() + 0.5, tpPos.getY(), tpPos.getZ() + 0.5);

                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 1));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 1800, 1));
            }
        }
    }

    private int getPlusMinusBound(int bound) {
        Random rand = new Random();
        int i = rand.nextInt(bound * 2);
        return (i - bound);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if (stack.getMetadata() == 0) {
            tooltip.add("Filled with energy!");
        }
        else {
            tooltip.add("Portable End Portal.");
        }
    }
}
