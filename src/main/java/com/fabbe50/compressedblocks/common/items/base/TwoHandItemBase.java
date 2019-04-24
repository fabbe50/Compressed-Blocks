package com.fabbe50.compressedblocks.common.items.base;

import com.fabbe50.compressedblocks.common.creativetabs.CBTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe50 on 06/02/2017.
 */
public class TwoHandItemBase extends ItemMap {
    public TwoHandItemBase(String name, CreativeTabs tab) {
        this.setHasSubtypes(false);
        setItemName(this, name);
        setCreativeTab(tab);
    }

    public static void setItemName(Item item, String itemName) {
        item.setRegistryName(itemName);
        item.setTranslationKey(item.getRegistryName().toString());
    }

    @Override
    public boolean isMap() {
        return false;
    }

    public static ItemStack setupNewMap(World p_190906_0_, double p_190906_1_, double p_190906_3_, byte p_190906_5_, boolean p_190906_6_, boolean p_190906_7_) {
        return null;
    }

    @Nullable
    @SideOnly(Side.CLIENT)
    public static MapData loadMapData(int mapId, World worldIn) {
        return null;
    }

    @Override
    @Nullable
    public MapData getMapData(ItemStack stack, World worldIn) {
        return null;
    }

    @Override
    public void updateMapData(World worldIn, Entity viewer, MapData data) {

    }

    public static void renderBiomePreviewMap(World p_190905_0_, ItemStack p_190905_1_) {
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

    }

    @Nullable
    public Packet<?> createMapDataPacket(ItemStack stack, World worldIn, EntityPlayer player) {
        return null;
    }

    @Override
    public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {

    }


    protected static void scaleMap(ItemStack p_185063_0_, World p_185063_1_, int p_185063_2_) {

    }

    protected static void enableMapTracking(ItemStack p_185064_0_, World p_185064_1_) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World playerIn, List<String> tooltip, ITooltipFlag advanced) {

    }

    @SideOnly(Side.CLIENT)
    public static int getColor(ItemStack p_190907_0_) {
        return 0;
    }
}
