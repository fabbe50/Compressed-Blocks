package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.reference.SubTypeRef;
import com.fabbe50.compressedblocks.reference.Textures;
import com.fabbe50.compressedblocks.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by fabbe50 on 27/01/2016.
 */
public class BlockPotato extends Block {
    @SideOnly(Side.CLIENT)
    private IIcon[] texture;

    static String[] subBlocks = SubTypeRef.potato;

    public BlockPotato() {
        super(Material.rock);
        setHardness(2.0F);
        setResistance(6.0F);

        setCreativeTab(CreativeTabCB.CB_TAB);
        setStepSound(soundTypeSnow);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < subBlocks.length; i++) {
            list.add(new ItemStack(block, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        texture = new IIcon[subBlocks.length];

        for (int i = 0; i < subBlocks.length; i++) {
            texture[i] = iconRegister.registerIcon(Textures.RESOURCE_PREFIX + "comprpotato-" + subBlocks[i]);
        }
    }



    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return texture[meta];
    }

    public int damageDropped(int meta) {
        return meta;
    }
}