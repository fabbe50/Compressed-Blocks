package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.reference.Textures;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

import java.util.List;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class BlockCB extends Block{
    private Block beaconBase;

    public BlockCB(Material material) {
        super(material);
        this.setCreativeTab(CreativeTabCB.CB_TAB2);
    }

    public BlockCB() {
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
        // tile.modid:blockname.name
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public Block setBeaconBase(){
        return beaconBase = this;
    }

    public Block setBeaconBaseForMeta(boolean bool) {
        if (bool)
            return beaconBase = this;
        else
            return beaconBase = null;
    }

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return this == beaconBase;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (!Loader.isModLoaded("Waila")) {
            list.add("");
            list.add(ColorHelper.tooltip + "Compressed Blocks 2");
        }
    }
}
