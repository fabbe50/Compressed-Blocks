package com.fabbe50.compressedblocks.core.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Created by fabbe50 on 12/10/2016.
 */
public class PacketTile extends PacketModBase {
    public static void init() {
        PacketHandler.INSTANCE.registerPacket(PacketTile.class);
    }

    public PacketTile() {

    }

    public PacketTile(TileEntity tileEntity) {
        addInt(tileEntity.getPos().getX());
        addInt(tileEntity.getPos().getY());
        addInt(tileEntity.getPos().getZ());
    }

    @Override
    public void handlePacket(EntityPlayer player, boolean isServer) {
        BlockPos blockPos = new BlockPos(getInt(), getInt(), getInt());
        TileEntity tileEntity = player.world.getTileEntity(blockPos);

        if (tileEntity instanceof ITilePacketHandler) {
            ((ITilePacketHandler)tileEntity).handleTilePacket(this, isServer);
            //tileEntity.getWorld().markAndNotifyBlock(tileEntity.getPos(), );
        }
    }
}
