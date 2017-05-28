package com.fabbe50.compressedblocks.core.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 26/09/2016.
 */
public class CBLibrary {
    //These are blocks that both have an item and a block.
    public static List<String> compressedBlocks = new ArrayList<>();

    public static void init() {
        compressedBlocks.add("potato");
        compressedBlocks.add("iron");
        compressedBlocks.add("gold");
        compressedBlocks.add("diamond");
    }
}
