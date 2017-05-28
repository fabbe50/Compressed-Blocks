package com.fabbe50.compressedblocks.core.asm;

import java.util.HashMap;

/**
 * Created by fabbe on 13/05/2017.
 */
public class ClassnameMap extends HashMap<String, String> {
    public ClassnameMap(String... s) {
        for(int i = 0; i < s.length / 2; i++)
            put(s[i * 2], s[i * 2 + 1]);
    }

    @Override
    public String put(String key, String value) {
        return super.put("L" + key + ";", "L" + value + ";");
    }
}
