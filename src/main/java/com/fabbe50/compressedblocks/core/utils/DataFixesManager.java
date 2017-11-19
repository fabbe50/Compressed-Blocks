package com.fabbe50.compressedblocks.core.utils;

import com.fabbe50.compressedblocks.common.entities.EntitySquidColored;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityBrewer;
import com.fabbe50.compressedblocks.common.tileentities.TileEntitySuperShulkerBox;
import com.fabbe50.compressedblocks.core.utils.datafix.SuperShulkerBoxEntityColor;
import com.fabbe50.compressedblocks.core.utils.datafix.SuperShulkerBoxItemColor;
import com.fabbe50.compressedblocks.core.utils.datafix.SuperShulkerBoxTileColor;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;

/**
 * Created by fabbe50 on 19/02/2017.
 */
public class DataFixesManager {
    private static void registerFixes(DataFixer dataFixer) {
        dataFixer.registerFix(FixTypes.ENTITY, new SuperShulkerBoxEntityColor());
        dataFixer.registerFix(FixTypes.ITEM_INSTANCE, new SuperShulkerBoxItemColor());
        dataFixer.registerFix(FixTypes.BLOCK_ENTITY, new SuperShulkerBoxTileColor());
    }

    public static DataFixer createFixer() {
        DataFixer dataFixer = new DataFixer(1);
        dataFixer = new net.minecraftforge.common.util.CompoundDataFixer(dataFixer);

        TileEntitySuperShulkerBox.registerFixesSuperShulkerBox(dataFixer);
        TileEntityBrewer.registerFixesBrewingStand(dataFixer);
        EntitySquidColored.registerFixesSquidColored(dataFixer);

        registerFixes(dataFixer);
        return dataFixer;
    }
}
