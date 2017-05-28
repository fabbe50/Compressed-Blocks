package com.fabbe50.compressedblocks.core.utils.datafix;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

/**
 * Created by fabbe50 on 19/02/2017.
 */
public class SuperShulkerBoxTileColor implements IFixableData {
    public int getFixVersion() {
        return 1;
    }

    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        if ("minecraft:shulker".equals(compound.getString("id"))) {
            compound.removeTag("Color");
        }

        return compound;
    }
}
