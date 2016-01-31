package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.lib.EnumPotato;
import com.fabbe50.compressedblocks.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by fabbe50 on 30/01/2016.
 */
public class BlockPotatoCompr extends Block {
    protected IIcon[] icons;

    public BlockPotatoCompr() {
        super(Material.rock);

        this.setHardness(3.0f);
        this.setResistance(15.0f);
        this.setStepSound(Block.soundTypeSnow);
        this.setBlockName("comprpotato");
        this.setCreativeTab(CreativeTabCB.CB_TAB);

        for(EnumPotato potato : EnumPotato.values()) {
            this.setHarvestLevel("pickaxe", potato.getHarvestLevel(), potato.getMeta());
        }
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (EnumPotato potato : EnumPotato.values()) {
            list.add(new ItemStack(item, 1, potato.getMeta()));
        }
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        icons = new IIcon[EnumPotato.count()];
        String prefix = Textures.RESOURCE_PREFIX + "comprpotato-";

        for (EnumPotato potato : EnumPotato.values()) {
            icons[potato.getMeta()] = reg.registerIcon(prefix + potato.getName().toLowerCase());
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta >= 0 && meta < EnumPotato.count()) {
            return icons[meta];
        }

        return null;
    }
}
