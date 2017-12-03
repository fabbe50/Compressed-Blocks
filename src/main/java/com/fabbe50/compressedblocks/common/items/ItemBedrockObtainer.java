package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.common.items.base.ItemBaseEnchanted;
import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.fabbe50.compressedblocks.core.utils.Utilities;
import com.fabbe50.compressedblocks.core.utils.helper.ChatHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by fabbe50 on 02/02/2017.
 */
public class ItemBedrockObtainer extends ItemBaseEnchanted {
    private int durability;

    public ItemBedrockObtainer(String itemName, CreativeTabs tab) {
        super(itemName, tab);
        this.setMaxStackSize(1);
        durability = setDurability();
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos).getBlock() == Blocks.BEDROCK) {
                Utilities.destroyBlock(worldIn, pos, false, true, true);
                durability--;
                if (durability == 0) {
                    if (player.getHeldItemMainhand().getItem() == ItemRegistry.BEDROCK_OBTAINER) {
                        player.getHeldItemMainhand().setCount(0);
                    } else if (player.getHeldItemOffhand().getItem() == ItemRegistry.BEDROCK_OBTAINER) {
                        player.getHeldItemOffhand().setCount(0);
                    }
                }
                return EnumActionResult.SUCCESS;
            } else {
                return EnumActionResult.FAIL;
            }
        }
        return EnumActionResult.FAIL;
    }

    private int setDurability() {
        return 16;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add(ChatHelper.MAGENTA + "EXPERIMENTAL");
        tooltip.add("");
        tooltip.add("It obtains bedrock!");
    }
}
