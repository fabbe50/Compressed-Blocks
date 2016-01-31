package com.fabbe50.compressedblocks.lib;

/**
 * Created by fabbe50 on 30/01/2016.
 */
public enum EnumPotato {
    Single(0, "single", 1),
    Double(1, "double", 1),
    Triple(2, "triple", 2),
    Quadruple(3, "quadruple", 2),
    Quintuple(4, "quintuple", 3),
    Sextuple(5, "sextuple", 3),
    Septuple(6, "septuple", 4),
    Octuple(7, "octuple", 4);

    private final int meta;
    private final String name;
    private final int harvestLevel;


    private EnumPotato(int meta, String name, int harvestLevel) {
        this.meta = meta;
        this.name = name;
        this.harvestLevel = harvestLevel;
    }

    public int getMeta() {
        return this.meta;
    }

    public String getName() {
        return this.name;
    }

    public int getHarvestLevel() {
        return this.harvestLevel;
    }

    public static int count() {
        return values().length;
    }
}
