package com.fabbe50.compressedblocks.common.blocks;

import com.fabbe50.compressedblocks.common.blocks.base.BlockBase;
import com.fabbe50.compressedblocks.core.lib.Payments;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by fabbe50 on 30/04/2017.
 */
public class BlockEndgamium extends BlockBase {
    public BlockEndgamium(Material material, MapColor mapColor, String blockName, float hardness, float resistance, @Nullable CreativeTabs tab) {
        super(material, mapColor, blockName, hardness, resistance, tab);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (Payments.payment.contains(playerIn.getHeldItem(hand).getItem())) {
                EntityItem item = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ());
                Random rand = new Random();
                int r = rand.nextInt(1001);
                if (playerIn.getHeldItem(hand).getItem() == Items.AIR) {
                    r = 1000;
                }
                LogHelper.info(r);
                if (r >= 1000) {
                    item.setPosition(pos.getX() + getPlusMinusBound(20), pos.getY() + getPlusMinusBound(20), pos.getZ() + getPlusMinusBound(20));
                    if (item.posY <= 0)
                        item.posY = 1;
                    item.setItem(new ItemStack(Blocks.DIAMOND_BLOCK));
                    worldIn.spawnEntity(item);
                }
                else if (r <= 999 && r >= 800) {

                }
                else if (r <= 799 && r >= 600) {

                }
                else if (r <= 599 && r >= 400) {

                }
                else if (r <= 399) {

                }
            }
        }
        return false;
    }

    private int getPlusMinusBound(int bound) {
        Random rand = new Random();
        int i = rand.nextInt(bound * 2);
        return (i - bound);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, BlockPos pos, BlockPos beacon) {
        return true;
    }
}
