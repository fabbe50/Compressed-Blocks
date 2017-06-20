package com.fabbe50.compressedblocks.core.lib;

import net.minecraft.util.IStringSerializable;

/**
 * Created by fabbe on 07/06/2017.
 */
public enum EnumLight implements IStringSerializable {
    LIGHT0(0, "0", 0),
    LIGHT1(1, "1", 1),
    LIGHT2(2, "2", 2),
    LIGHT3(3, "3", 3),
    LIGHT4(4, "4", 4),
    LIGHT5(5, "5", 5),
    LIGHT6(6, "6", 6),
    LIGHT7(7, "7", 7),
    LIGHT8(8, "8", 8),
    LIGHT9(9, "9", 9),
    LIGHT10(10, "10", 10),
    LIGHT11(11, "11", 11),
    LIGHT12(12, "12", 12),
    LIGHT13(13, "13", 13),
    LIGHT14(14, "14", 14),
    LIGHT15(15, "15", 15);

    private static final EnumLight[] META_LOOKUP = new EnumLight[values().length];
    private int meta;
    private String name;
    private int lightLevel;

    EnumLight(int meta, String name, int lightLevel) {
        this.meta = meta;
        this.name = name;
        this.lightLevel = lightLevel;
    }

    public int getMetadata() {
        return this.meta;
    }

    public int getLightLevel() {
        return this.lightLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    public static EnumLight byMetadata(int meta){
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }
        return META_LOOKUP[meta];
    }

    static{
        for (EnumLight e : values()){
            META_LOOKUP[e.getMetadata()] = e;
        }
    }
}
