package com.fabbe50.compressedblocks.init;

import com.fabbe50.compressedblocks.reference.ModItemLibrary;
import com.fabbe50.compressedblocks.utility.LogHelper;

/**
 * Created by fabbe50 on 26/01/2016.
 */
public class ItemImport {
    public static void init() {
        if (ModItemLibrary.compr8cobble == null) {
            LogHelper.warn("compr7cobble is null");
        }
        if (ModItemLibrary.crystalCluster == null) {
            LogHelper.warn("crystalCluster is null");
        }
        if (ModItemLibrary.unstableBlock == null) {
            LogHelper.warn("unstableBlock is null");
        }
        if (ModItemLibrary.xpDrainFoci == null) {
            LogHelper.warn("xpDrainFoci is null");
        }
        if (ModItemLibrary.starBlock == null) {
            LogHelper.warn("starBlock is null");
        }
        if (ModItemLibrary.gaiaIngot == null) {
            LogHelper.warn("gaiaIngot is null");
        }
        if (ModItemLibrary.brewOfFlowingSpirit == null) {
            LogHelper.warn("brewOfFlowingSpirit is null");
        }
        if (ModItemLibrary.iridium == null) {
            LogHelper.warn("iridium is null");
        }
        if (ModItemLibrary.enderium == null) {
            LogHelper.warn("enderium is null");
        }
    }
}
