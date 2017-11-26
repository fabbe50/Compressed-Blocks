package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.common.entities.EntityPotatoSingularity;
import com.fabbe50.compressedblocks.common.entities.EntityPotatoSingularityVanilla;
import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe on 21/07/2017 - 12:45 PM - 12:45 PM.
 */
public class ItemPotatoSingularity extends ItemBase {
    public ItemPotatoSingularity(String itemName, CreativeTabs tab) {
        super(itemName, tab);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote && Loader.isModLoaded("brandonscore") && Loader.isModLoaded("draconicevolution")) {
            LogHelper.info("Draconic Evolution is loaded!");
            LogHelper.info("Implementing DE-Reactor Explosion");
            EntityPotatoSingularity entity = new EntityPotatoSingularity(worldIn, playerIn);
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.8F, 1.0F);
            worldIn.spawnEntity(entity);
        }
        else {
            EntityPotatoSingularityVanilla entity = new EntityPotatoSingularityVanilla(worldIn, playerIn);
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0f, 1.8f, 1.0f);
            worldIn.spawnEntity(entity);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add("Infinite Potatoes");
    }
}
