package com.fabbe50.compressedblocks.core.handler;

import com.fabbe50.compressedblocks.common.blocks.BlockChunkScanner;
import com.fabbe50.compressedblocks.core.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 17/12/2017 - 7:29 AM.
 */
public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Reference.GUI_CHUNKSCANNER) {
            return new BlockChunkScanner.GuiChunkScanner();
        }

        return null;
    }


}
