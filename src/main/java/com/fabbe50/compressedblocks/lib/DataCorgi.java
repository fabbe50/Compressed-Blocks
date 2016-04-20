package com.fabbe50.compressedblocks.lib;

import com.fabbe50.compressedblocks.common.entity.EnumCorgiTypes;

/**
 * Created by fabbe50 on 25/02/2016.
 */
public class DataCorgi {
    static String[] NAME;

    public static void init() {
        NAME = new String[EnumCorgiTypes.count()];
        createCorgiNameList();
    }

    private static void createCorgiNameList() {
        for (EnumCorgiTypes i : EnumCorgiTypes.values()) {
            NAME[i.getCorgiID()] = i.getCorgiName();
        }
    }

    public static String getCorgiName(int i) {
        return NAME[i];
    }
}