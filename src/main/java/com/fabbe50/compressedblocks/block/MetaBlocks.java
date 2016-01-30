package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.reference.Reference;
import com.fabbe50.compressedblocks.reference.Textures;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe50 on 27/01/2016.
 */
public class MetaBlocks extends ItemBlock {
    public MetaBlocks(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        String name = "";
        switch (itemstack.getItemDamage())
        {
            case 0:
            {
                name = "single";
                break;
            }
            case 1:
            {
                name = "double";
                break;
            }
            case 2:
            {
                name = "triple";
                break;
            }
            case 3:
            {
                name = "quadruple";
                break;
            }
            case 4:
            {
                name = "quintuple";
                break;
            }
            case 5:
            {
                name = "sextuple";
                break;
            }
            case 6:
            {
                name = "septuple";
                break;
            }
            case 7:
            {
                name = "octuple";
                break;
            }
            default:
                name = "";
        }
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + "." + name);
    }

    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}