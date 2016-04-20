package com.fabbe50.compressedblocks.common.block;

import com.fabbe50.compressedblocks.CompressedBlocks;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityBlazeGen;
import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.reference.Textures;
import com.fabbe50.compressedblocks.init.ModTileEntities;
import com.fabbe50.compressedblocks.utility.MathHelper;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 20/04/2016.
 */
public class BlockBlazeGen extends BlockContainer {

    private final boolean isActive;

    @SideOnly(Side.CLIENT)
    private IIcon iconFront;
    @SideOnly(Side.CLIENT)
    private IIcon iconTop;

    public BlockBlazeGen (boolean isActive) {
        super(Material.iron);

        this.isActive = isActive;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + "blazegen_side");
        this.iconTop = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + "blazegen_top");
        this.iconFront = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + (this.isActive ? "blazegen_front_active" : "blazegen_front_idle"));
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        return side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side != metadata ? this.blockIcon : this.iconFront));
    }

    public Item getItemDropped(World world, int x, int y, int z) {
        return Item.getItemFromBlock(ModTileEntities.blazegenidle);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.setDefaultDirection(world, x, y, z);
    }

    private void setDefaultDirection(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z - 1);
            Block block2 = world.getBlock(x, y, z + 1);
            Block block3 = world.getBlock(x - 1, y, z);
            Block block4 = world.getBlock(x + 1, y, z);

            byte b0 = 3;

            if (block.func_149730_j() && !block2.func_149730_j()) {
                b0 = 3;
            }if (block2.func_149730_j() && !block.func_149730_j()) {
                b0 = 2;
            }if (block3.func_149730_j() && !block4.func_149730_j()) {
                b0 = 5;
            }if (block4.func_149730_j() && !block3.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && ConfigurationHandler.noguiblockshavegui) {
            FMLNetworkHandler.openGui(player, CompressedBlocks.instance, Reference.GuiIDBlazeGen, world, x, y, z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityBlazeGen();
    }

    //TODO randomDisplayTick

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityPlayer, ItemStack itemstack) {
        int i = net.minecraft.util.MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0f / 360f) + 0.50) & 3;

        if (i == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }if (i == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }if (i == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }if (i == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (itemstack.hasDisplayName()) {
            ((TileEntityBlazeGen)world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }
    }
}
