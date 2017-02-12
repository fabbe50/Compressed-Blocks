package com.fabbe50.compressedblocks.core.network;

/**
 * Created by fabbe50 on 12/10/2016.
 */
public interface ITilePacketHandler {
    public void handleTilePacket(PacketModBase payload, boolean isServer);
}
