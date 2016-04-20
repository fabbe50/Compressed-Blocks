package com.fabbe50.compressedblocks.common.block;

import com.fabbe50.compressedblocks.core.handler.ConfigurationHandler;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class BlockEndgamiumC extends BlockCB{
    public BlockEndgamiumC() {
        super();
        this.setBlockName("endgamiumblockc");
        this.setBlockTextureName("endgamiumblockc");

        this.setLightLevel(ConfigurationHandler.lightLevelForEndgamium);
        this.setResistance(2500.0f);
        this.setHardness(90.0f);
        this.setStepSound(soundTypeMetal);

        this.setBeaconBase();
    }
}
