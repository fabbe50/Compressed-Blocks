package com.fabbe50.compressedblocks.core.handler;

import com.fabbe50.compressedblocks.common.container.ContainerBlazeGen;
import com.fabbe50.compressedblocks.common.tileentities.TileEntityBlazeGen;
import com.fabbe50.compressedblocks.core.client.gui.GUIBlazeGen;
import com.fabbe50.compressedblocks.core.reference.Reference;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by fabbe50 on 12/04/2016.
 */
public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity != null) {
            switch(ID) {
                case Reference.GuiIDBlazeGen:
                    if (entity instanceof TileEntityBlazeGen) {
                        return new ContainerBlazeGen(player.inventory, (TileEntityBlazeGen)entity);
                    }
                    return null;
            }
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);

        if (entity != null) {
            switch(ID) {
                case Reference.GuiIDBlazeGen:
                    if (entity instanceof TileEntityBlazeGen) {
                        return new GUIBlazeGen(player.inventory, (TileEntityBlazeGen)entity);
                    }
                    return null;
            }
        }

        return null;
    }
}
