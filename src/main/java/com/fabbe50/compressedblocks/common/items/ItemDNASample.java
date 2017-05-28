package com.fabbe50.compressedblocks.common.items;

import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.utility.helper.ChatHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;
import java.util.Objects;

/**
 * Created by fabbe50 on 23/04/2017.
 */
public class ItemDNASample extends ItemBase {
    public ItemDNASample(String itemName, CreativeTabs tab) {
        super(itemName, tab);
        this.setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        if (!worldIn.isRemote) {
            NBTTagCompound tags = player.getHeldItem(hand).getTagCompound();

            if (tags == null)
                tags = new NBTTagCompound();

            if (player.isSneaking()) {
                tags.setString("player", "");
            }
            else {
                tags.setString("player", player.getDisplayName().getUnformattedText());
            }

            player.getHeldItem(hand).setTagCompound(tags);
            return ActionResult.newResult(EnumActionResult.SUCCESS, player.getHeldItem(hand));
        }
        return ActionResult.newResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!player.getEntityWorld().isRemote) {
            NBTTagCompound tags = stack.getTagCompound();

            if (tags == null)
                tags = new NBTTagCompound();

            if (entity instanceof EntityPlayer)
                tags.setString("player", entity.getDisplayName().getUnformattedText());
            else
                return false;

            stack.setTagCompound(tags);
            return true;
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(ChatHelper.MAGENTA + "Insert nice looking texture here.");
        tooltip.add("");

        if (stack.getTagCompound() != null) {
            if (!Objects.equals(stack.getTagCompound().getString("player"), ""))
                tooltip.add("Currently containing sample of: " + ChatHelper.MAGENTA + stack.getTagCompound().getString("player"));
            else
                tooltip.add("Currently Empty");
        }
        else {
            tooltip.add("Currently Empty");
        }

        if (stack.getTagCompound() == null) {
            tooltip.add("");
            tooltip.add("Right Click to sample yourself.");
            tooltip.add("Left Click on another player to sample that player.");
        }
        else {
            if (!Objects.equals(stack.getTagCompound().getString("player"), "")) {
                tooltip.add("");
                tooltip.add("Shift Right Click to empty.");
            }
            else {
                tooltip.add("");
                tooltip.add("Right Click to sample yourself.");
                tooltip.add("Left Click on another player to sample that player.");
            }
        }
    }
}
