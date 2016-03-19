package com.fabbe50.compressedblocks.lib;

/**
 * Created by fabbe50 on 30/01/2016.
 */
public enum EnumCompressed {
    Single(0, "single", 0, 3, 15, false, "9"),
    Double(1, "double", 0, 5, 17, false, "81"),
    Triple(2, "triple", 1, 9, 21, false, "729"),
    Quadruple(3, "quadruple", 1, 15, 27, false, "6,561"),
    Quintuple(4, "quintuple", 2, 23, 35, false, "59,049"),
    Sextuple(5, "sextuple", 2, 33, 45, true, "531,441"),
    Septuple(6, "septuple", 3, 45, 57, true, "4,782,969"),
    Octuple(7, "octuple", 3, 59, 71,true, "43,046,721");

    private final int meta;
    private final String name;
    private final int harvestLevel;
    private final int resistance;
    private final int hardness;
    private boolean isABeaconBlock;
    private String flavorText;


    EnumCompressed(int meta, String name, int harvestLevel, int resistance, int hardness, boolean isABeaconBlock, String flavorText) {
        this.meta = meta;
        this.name = name;
        this.harvestLevel = harvestLevel;
        this.resistance = resistance;
        this.hardness = hardness;
        this.isABeaconBlock = isABeaconBlock;
        this.flavorText = flavorText;
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

    public int getResistance() { return this.resistance; }

    public int getHardness() { return this.hardness; }

    public boolean getBeaconBase() {return this.isABeaconBlock;}

    public String getFlavorText() {return this.flavorText;}

    public static int count() {
        return values().length;
    }
}
