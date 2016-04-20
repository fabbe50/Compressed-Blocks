package com.fabbe50.compressedblocks.common.block;

import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class BlockStar extends BlockCB{
    public BlockStar() {
        super();
        this.setBlockName("starblock");
        this.setBlockTextureName("starblock");

        this.setLightLevel(ConfigurationHandler.lightLevelForStarblock);
        this.setResistance(1700.0f);
        this.setHardness(40.0f);
        this.setStepSound(soundTypeMetal);

        this.setBeaconBase();
    }
}
