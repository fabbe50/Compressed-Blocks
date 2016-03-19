package com.fabbe50.compressedblocks.block;

import com.fabbe50.compressedblocks.creativetab.CreativeTabCB;
import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by fabbe50 on 15/01/2016.
 */
public class BlockEndgamium extends BlockCB {
    public BlockEndgamium() {
        super();
        this.setBlockName("endgamiumblock");
        this.setBlockTextureName("endgamiumblock");

        this.setLightLevel(ConfigurationHandler.lightLevelForEndgamium);
        this.setResistance(2000.0f);
        this.setHardness(75.0f);
        this.setStepSound(soundTypeMetal);

        this.setBeaconBase();
    }
}
