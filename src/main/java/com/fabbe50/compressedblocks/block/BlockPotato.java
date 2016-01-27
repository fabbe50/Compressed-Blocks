package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.init.ModBlocks;
import com.fabbe50.compressedblocks.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by fabbe50 on 27/01/2016.
 */
public class BlockPotato extends Block {
    public BlockPotato() {
        super(Material.snow);
        setBlockName("comprpotato");
        setHardness(2.0f);
        setResistance(1.0f);
        setStepSound(soundTypeSnow);
    }

    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[7];

        for (int i = 0; i < icons.length; i++)
        {
            icons[i] = par1IconRegister.registerIcon(Reference.MOD_ID + ":" + (this.getUnlocalizedName().substring(5)) + i);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int par1, int par2)
    {
        switch (par2)
        {
            case 0:
                return icons[0];
            case 1:
            {
                switch (par1)
                {
                    case 0:
                        return icons[1];
                    case 1:
                        return icons[2];
                    case 2:
                        return icons[3];
                    case 3:
                        return icons[4];
                    case 4:
                        return icons[5];
                    case 5:
                        return icons[6];
                    case 6:
                        return icons[7];
                    case 7:
                        return icons[8];
                    default:
                        return icons[1];
                }
            }
            default:
            {
                System.out.println("Invalid metadata for " + this.getUnlocalizedName());
                return icons[0];
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2, List par3List)
    {
        for (int i = 0; i < 2; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));

            par1.setCreativeTab(CreativeTabCB.CB_TAB);
        }
    }
}
