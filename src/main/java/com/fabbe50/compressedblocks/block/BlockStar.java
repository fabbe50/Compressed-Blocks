package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import net.minecraft.world.IBlockAccess;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class BlockStar extends BlockCB{
    public BlockStar() {
        super();
        this.setBlockName("comprstarblock");
        this.setBlockTextureName("comprstarblock");

        this.setLightLevel(ConfigurationHandler.lightLevelForStarblock);
        this.setResistance(1700.0f);
        this.setHardness(40.0f);
        this.setStepSound(soundTypeMetal);
    }
}
