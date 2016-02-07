package com.fabbe50.compressedblocks.lib;

/**
 * Created by fabbe50 on 07/02/2016.
 */
public class DataCompressed {
    static String[] Flavor;

    public static void init() {
        Flavor = new String[EnumCompressed.count()];
        createCompression();
    }

    private static void createCompression() {
        for (EnumCompressed i : EnumCompressed.values()) {
            Flavor[i.getMeta()] = i.getFlavorText();
        }
    }

    public static String getCompression(int i) {
        return Flavor[i];
    }
}
