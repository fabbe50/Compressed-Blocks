package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.fabbe50.compressedblocks.core.utils.helper.TeleportHelper;
import com.thefifthidiot.tficore.common.items.ItemBase;
import com.thefifthidiot.tficore.utility.helper.ChatHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by fabbe50 on 26/02/2017.
 */
public class ItemTeleportOrb extends ItemBase {
    public ItemTeleportOrb(String itemName, CreativeTabs tab) {
        super(itemName, tab);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {
        if (!worldIn.isRemote) {
            if (player.isSneaking()) {
                ItemStack stack = player.getHeldItem(hand);
                NBTTagCompound tags = stack.getTagCompound();

                if (tags == null) {
                    tags = new NBTTagCompound();
                }

                BlockPos pos = player.getPosition();

                tags.setDouble("posX", pos.getX() + 0.5);
                tags.setDouble("posY", pos.getY());
                tags.setDouble("posZ", pos.getZ() + 0.5);
                tags.setInteger("dimension", player.dimension);

                player.sendMessage(new TextComponentString("Set target to: {X: " + pos.getX() + ", Y: " + pos.getY() + ", Z: " + pos.getZ() + ", Dim: " + player.dimension + "}"));

                stack.setTagCompound(tags);
                hasEffect(stack);

                return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
            } else {
                ItemStack stack = player.getHeldItem(hand);
                NBTTagCompound tags = stack.getTagCompound();

                if (tags == null) {
                    if (tags.getSize() < 4) {
                        player.sendMessage(new TextComponentString("No Target Set"));
                        return ActionResult.newResult(EnumActionResult.FAIL, stack);
                    }
                }

                try {
                    TeleportHelper.teleport(worldIn, player, tags);

                    return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
                }
                catch (Exception e) {
                    return ActionResult.newResult(EnumActionResult.FAIL, stack);
                }
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL, player.getHeldItem(hand));
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getTagCompound() != null && stack.getTagCompound().getSize() >= 4;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        NBTTagCompound tags = stack.getTagCompound();

        tooltip.add(ChatHelper.MAGENTA + "EXPERIMENTAL");
        tooltip.add("");

        if (Configs.enableCrossDimensionalTP) {
            tooltip.add(ChatHelper.MAGENTA + "Doesn't work going in and out of The End");
        }
        else {
            tooltip.add(ChatHelper.MAGENTA + "Cross-dimensional Teleport is Disabled.");
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            if (tags != null && tags.getSize() >= 4) {
                    tooltip.add("X: " + tags.getInteger("posX"));
                    tooltip.add("Y: " + tags.getInteger("posY"));
                    tooltip.add("Z: " + tags.getInteger("posZ"));
                    if (Configs.enableCrossDimensionalTP)
                        tooltip.add("DIM: " + tags.getInteger("dimension"));
            }
            else {
                tooltip.add("No target set!");
            }
        }
        else {
            tooltip.add("Hold 'Shift' for more information.");
        }
    }
}
