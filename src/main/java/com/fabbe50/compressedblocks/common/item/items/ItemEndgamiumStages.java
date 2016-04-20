package com.fabbe50.compressedblocks.common.item.items;

import com.fabbe50.compressedblocks.common.item.ItemCB;
import com.fabbe50.compressedblocks.lib.EnumEndgamiumStages;
import com.fabbe50.compressedblocks.core.reference.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by fabbe50 on 14/03/2016.
 */
public class ItemEndgamiumStages extends ItemCB {
    public IIcon[] icons = new IIcon[EnumEndgamiumStages.count()];

    public ItemEndgamiumStages () {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName("endgamiumstages");
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        for (EnumEndgamiumStages stages : EnumEndgamiumStages.values()) {
            this.icons[stages.getMeta()] = reg.registerIcon(Reference.MOD_ID + ":endgamiumstages_" + stages.getName());
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > EnumEndgamiumStages.count()-1)
            meta = 0;

        return this.icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < icons.length; i ++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName (ItemStack itemStack) {                //Return a name that looks like tile.modid:blockid_metaname.name
        EnumEndgamiumStages endgamiumStages = EnumEndgamiumStages.values()[itemStack.getItemDamage()];
        return String.format("item.%s", getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + "_" + endgamiumStages.getName().toLowerCase());
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {  //Removes all mumbo jumbo from the Unlocalized name
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}