package com.fabbe50.compressedblocks.common.items;

import com.fabbe50.compressedblocks.common.blocks.meta.MetaCompressedBase;
import com.fabbe50.compressedblocks.common.entities.EntityCorgi;
import com.fabbe50.compressedblocks.common.items.base.ItemBase;
import com.fabbe50.compressedblocks.core.lib.EnumCompressed;
import com.fabbe50.compressedblocks.core.lib.EnumCorgiType;
import com.fabbe50.compressedblocks.core.registry.BlockRegistry;
import com.fabbe50.compressedblocks.core.registry.ItemRegistry;
import com.fabbe50.compressedblocks.core.utils.Utilities;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by fabbe50 on 06/02/2017.
 */
public class ItemPotatoBone extends ItemBase {
    public ItemPotatoBone(String itemName, CreativeTabs tab) {
        super(itemName, tab);
    }

    /*
    @Override
    @Nonnull
    @SuppressWarnings("unchecked")
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (worldIn.getBlockState(pos).getBlock() == BlockRegistry.COMPRESSED_POTATO && worldIn.getBlockState(pos) == BlockRegistry.COMPRESSED_POTATO.getDefaultState().withProperty(MetaCompressedBase.TYPE, EnumCompressed.OCTUPLE)) {
                Utilities.destroyBlock(worldIn, pos, false, false, true);
                EntityCorgi entityCorgi = new EntityCorgi(worldIn);
                entityCorgi.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                entityCorgi.setTamed(true);
                entityCorgi.setOwnerId(player.getUniqueID());
                entityCorgi.setHealth(60);
                if (!(player.getHeldItem(hand).getDisplayName()).equals("Potato on a Bone")) {
                    if (EnumCorgiType.byName((player.getHeldItem(hand).getDisplayName())) != null) {
                        entityCorgi.setCorgitype(EnumCorgiType.byName((player.getHeldItem(hand).getDisplayName())));
                    }
                    else {
                        LogHelper.info(EnumCorgiType.byName((player.getHeldItem(hand).getDisplayName())));
                    }
                }
                entityCorgi.setCustomNameTag(Utilities.upperCaseFirstLetter(EnumCorgiType.byDamage(entityCorgi.getCorgiType().getID()).getName()) + " Corgi");
                worldIn.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, true, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 0);
                worldIn.spawnEntity(entityCorgi);
                if (player.getHeldItemMainhand().getItem() == ItemRegistry.POTATO_BONE) {
                    player.getHeldItemMainhand().setCount(player.getHeldItemMainhand().getCount() - 1);
                } else if (player.getHeldItemOffhand().getItem() == ItemRegistry.POTATO_BONE) {
                    player.getHeldItemOffhand().setCount(player.getHeldItemOffhand().getCount() - 1);
                }
                return EnumActionResult.SUCCESS;
            }
            else {
                return EnumActionResult.FAIL;
            }
        }
        return EnumActionResult.FAIL;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        tooltip.add("It spawns Corgis when used on a octuple compressed potato block!");
        tooltip.add("");
        tooltip.add("Type of Corgi is randomized unless the potato on a bone");
        tooltip.add("is named to the wanted corgi's name.");
    }
    */
}
