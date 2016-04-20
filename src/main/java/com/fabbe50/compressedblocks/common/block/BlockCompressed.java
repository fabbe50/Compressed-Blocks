package com.fabbe50.compressedblocks.common.block;

import com.fabbe50.compressedblocks.core.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.lib.EnumCompressed;
import com.fabbe50.compressedblocks.core.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by fabbe50 on 09/03/2016.
 */
public class BlockCompressed extends BlockCB {
    private String[] name = new String[255];
    protected IIcon[] icons;
    private int i;

    public BlockCompressed (String name, int i) {
        super(Material.rock);

        this.i = i;
        this.name[i] = name;
        this.setStepSound(Block.soundTypeStone);
        this.setBlockName(name);
        this.setCreativeTab(CreativeTabCB.CB_TAB2);

        //Temporary hardcoded values til applying different values to different blocks is in my knowledge
        this.setHardness(25);
        this.setResistance(35);//


        for(EnumCompressed block : EnumCompressed.values()) {                      //Sets different levels of tools required depending on compression rate
            this.setHarvestLevel("pickaxe", block.getHarvestLevel(), block.getMeta());
            //this.setHardness(block.getHardness()); //Does not apply the different values to the different blocks
            //this.setResistance(block.getResistance()); //Does not apply the different values to the different blocks
            //this.setBeaconBaseForMeta(block.getBeaconBase()); //Does not separate the beacon data from the different blocks
        }
    }

    @Override
    public int damageDropped(int meta) {                                    //Returns the right metadata when item is dropped
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumCompressed block : EnumCompressed.values()) {
            list.add(new ItemStack(item, 1, block.getMeta()));
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icons = new IIcon[EnumCompressed.count()];                              //Setting icons array to be the length of the compressed block enum
        String prefix = Textures.RESOURCE_PREFIX + name[i] + "_";                  //Creating a prefix specifically for the compressed block

        for (EnumCompressed block : EnumCompressed.values()) {                 //For-loop for adding the textures to the blocks
            icons[block.getMeta()] = reg.registerIcon(prefix + block.getName().toLowerCase());
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {                                  //Makes sure that the right texture is displayed on the right block
        if (meta >= 0 && meta < EnumCompressed.count()) {
            return icons[meta];
        }
        return null;
    }
}
