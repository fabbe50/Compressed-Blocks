package com.fabbe50.compressedblocks.core.utils.datafix;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

/**
 * Created by fabbe50 on 19/02/2017.
 */
public class SuperShulkerBoxItemColor implements IFixableData {
    public static final String[] NAMES_BY_COLOR = new String[] {
            "compressedblocks:white_super_shulker_box", "compressedblocks:orange_super_shulker_box", "compressedblocks:magenta_super_shulker_box",
            "compressedblocks:light_blue_super_shulker_box", "compressedblocks:yellow_super_shulker_box", "compressedblocks:lime_super_shulker_box",
            "compressedblocks:pink_super_shulker_box", "compressedblocks:gray_super_shulker_box", "compressedblocks:silver_super_shulker_box",
            "compressedblocks:cyan_super_shulker_box", "compressedblocks:purple_super_shulker_box", "compressedblocks:blue_super_shulker_box",
            "compressedblocks:brown_super_shulker_box", "compressedblocks:green_super_shulker_box", "compressedblocks:red_super_shulker_box", "compressedblocks:black_super_shulker_box"};

    public int getFixVersion() {
        return 1;
    }

    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        if ("compressedblocks:supershulkerbox".equals(compound.getString("id")) && compound.hasKey("tag", 10)) {
            NBTTagCompound nbttagcompound = compound.getCompoundTag("tag");

            if (nbttagcompound.hasKey("BlockEntityTag", 10)) {
                NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("BlockEntityTag");

                if (nbttagcompound1.getTagList("Items", 10).isEmpty()) {
                    nbttagcompound1.removeTag("Items");
                }

                int i = nbttagcompound1.getInteger("Color");
                nbttagcompound1.removeTag("Color");

                if (nbttagcompound1.isEmpty()) {
                    nbttagcompound.removeTag("BlockEntityTag");
                }

                if (nbttagcompound.isEmpty()) {
                    compound.removeTag("tag");
                }

                compound.setString("id", NAMES_BY_COLOR[i % 16]);
            }
        }

        return compound;
    }
}
