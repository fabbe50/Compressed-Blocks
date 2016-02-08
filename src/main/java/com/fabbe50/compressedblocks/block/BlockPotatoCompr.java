package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.lib.EnumCompressed;
import com.fabbe50.compressedblocks.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

/**
 * Created by fabbe50 on 30/01/2016.
 */
public class BlockPotatoCompr extends Block {
    protected IIcon[] icons;
    private Block beaconBase;

    public BlockPotatoCompr() {
        super(Material.rock);

        this.setHardness(3.0f);
        this.setResistance(15.0f);
        this.setStepSound(Block.soundTypeSnow);
        this.setBlockName("comprpotato");
        this.setCreativeTab(CreativeTabCB.CB_TAB);

        for(EnumCompressed potato : EnumCompressed.values()) {                      //Sets different levels of tools required depending on compression rate
            this.setHarvestLevel("pickaxe", potato.getHarvestLevel(), potato.getMeta());

            //if (potato.getBeaconBase()) {
            //    meta = potato.getMeta();
            //    this.setBeaconBase();
            //}
        }
    }

    @Override
    public int damageDropped(int meta) {                                    //Returns the right metadata when item is dropped
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumCompressed potato : EnumCompressed.values()) {
            list.add(new ItemStack(item, 1, potato.getMeta()));
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icons = new IIcon[EnumCompressed.count()];                              //Setting icons array to be the length of the potato block enum
        String prefix = Textures.RESOURCE_PREFIX + "comprpotato_";              //Creating a prefix specifically for the compressed potatoes
        String suffix = "-alt" + ConfigurationHandler.textureAlternative;       //Creating a suffic specifically for the compressed potatoes

        for (EnumCompressed potato : EnumCompressed.values()) {                 //For-loop for adding the textures to the blocks
            icons[potato.getMeta()] = reg.registerIcon(prefix + potato.getName().toLowerCase() + suffix);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {                                  //Makes sure that the right texture is displayed on the right block
        if (meta >= 0 && meta < EnumCompressed.count()) {
            return icons[meta];
        }

        return null;
    }

    /*public Block setBeaconBase(){
        return beaconBase;
    }*/

    @Override
    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return false;
    }
}
