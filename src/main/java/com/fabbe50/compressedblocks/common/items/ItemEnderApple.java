package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
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
                if (!player.inventory.hasItemStack(new ItemStack(ItemRegistry.TRINKET, 1, 1))) {
                    player.changeDimension(1);
                }

                player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 400, 2));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 2));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 3200, 4));
                player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 3600, 3));
            }
            else {
                if (!player.inventory.hasItemStack(new ItemStack(ItemRegistry.TRINKET, 1, 1))) {
                    BlockPos tpPos = new BlockPos(player.posX + getPlusMinusBound(40), player.posY + getPlusMinusBound(20), player.posZ + getPlusMinusBound(40));
                    Block block1 = worldIn.getBlockState(tpPos).getBlock();
                    Block block2 = worldIn.getBlockState(tpPos.add(0, 1, 0)).getBlock();
                    worldIn.setBlockState(tpPos, Blocks.AIR.getDefaultState());
                    worldIn.setBlockState(tpPos.add(0, 1, 0), Blocks.AIR.getDefaultState());
                    if (worldIn.getBlockState(tpPos.add(0, -1, 0)).getBlock() == Blocks.AIR)
                        worldIn.setBlockState(tpPos.add(0, -1, 0), Blocks.BARRIER.getDefaultState());
                    player.attemptTeleport(tpPos.getX() + 0.5, tpPos.getY(), tpPos.getZ() + 0.5);
                    worldIn.setBlockState(tpPos, block1.getDefaultState());
                    worldIn.setBlockState(tpPos.add(0, 1, 0), block2.getDefaultState());
                    if (worldIn.getBlockState(tpPos.add(0, -1, 0)).getBlock() == Blocks.BARRIER)
                        worldIn.setBlockState(tpPos.add(0, -1, 0), Blocks.AIR.getDefaultState());
                }

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
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(this));
        subItems.add(new ItemStack(this, 1, 1));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        if (stack.getMetadata() == 0) {
            tooltip.add("Filled with energy!");
        }
        else {
            tooltip.add("You hear Endermen inside.");
        }
    }
}
