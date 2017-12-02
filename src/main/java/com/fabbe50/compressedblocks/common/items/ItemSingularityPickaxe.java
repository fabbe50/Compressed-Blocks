package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.registry.ToolMaterialRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by fabbe on 02/12/2017 - 12:51 AM.
 */
public class ItemSingularityPickaxe extends ItemPickaxe {
    public ItemSingularityPickaxe(String name) {
        super(ToolMaterialRegistry.SINGULARITY);
        setItemName(name);
    }

    public void setItemName(String name) {
        setRegistryName(Reference.MOD_ID, name);
        setUnlocalizedName(name);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.AIR) {
                if (player.inventory.hasItemStack(new ItemStack(Blocks.TORCH))) {
                    player.inventory.decrStackSize(player.inventory.getSlotFor(new ItemStack(Blocks.TORCH)), 1);
                    worldIn.setBlockState(pos.offset(facing), Blocks.TORCH.getDefaultState());
                    return EnumActionResult.SUCCESS;
                }
            }
            return EnumActionResult.FAIL;
        }
        return EnumActionResult.PASS;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isSneaking()) {


            return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
        }
        return ActionResult.newResult(EnumActionResult.PASS, stack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        return false;
    }
}
