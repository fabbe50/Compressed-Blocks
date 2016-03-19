package com.fabbe50.compressedblocks.item.itemblocks;

import com.fabbe50.compressedblocks.block.BlockCompressed;
import com.fabbe50.compressedblocks.lib.DataCompressed;
import com.fabbe50.compressedblocks.lib.EnumCompressed;
import com.fabbe50.compressedblocks.reference.Textures;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by fabbe50 on 09/03/2016.
 */
public class ItemCompressedBlock extends ItemBlock{

    public ItemCompressedBlock(Block block) {
        super(block);
        this.setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public int getMetadata(int meta) {                                      //Gives the placed block the right metavalue
        return meta;
    }

    @Override
    public String getUnlocalizedName (ItemStack itemStack) {                //Return a name that looks like tile.modid:blockid_metaname.name
        EnumCompressed block = EnumCompressed.values()[itemStack.getItemDamage()];
        return String.format("tile.%s", getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + "_" + block.getName().toLowerCase());
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {  //Removes all mumbo jumbo from the Unlocalized name
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        int meta = stack.getItemDamage();

        list.add(DataCompressed.getCompression(meta) + " " + StatCollector.translateToLocal(getUnlocalizedName() + ".flavor"));
    }
}
