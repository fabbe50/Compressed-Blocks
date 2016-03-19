package com.fabbe50.compressedblocks.lib;

/**
 * Created by fabbe50 on 14/03/2016.
 */
public enum EnumEndgamiumStages {
    MESH("mesh", 0);


    private final String name;
    private final int meta;

    EnumEndgamiumStages (String name, int meta) {
        this.name = name;
        this.meta = meta;
    }

    public String getName () {
        return name;
    }

    public int getMeta () {
        return meta;
    }

    public static int count() {
        return values().length;
    }
}