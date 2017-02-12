package com.fabbe50.compressedblocks.core.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;

/**
 * Created by fabbe50 on 11/10/2016.
 */
@ChannelHandler.Sharable
public class PacketHandler extends MessageToMessageCodec<FMLProxyPacket, PacketBase> {
    public static final PacketHandler INSTANCE = new PacketHandler();

    private EnumMap<Side, FMLEmbeddedChannel> channels;
    private final LinkedList<Class<? extends PacketBase>> packets = new LinkedList<Class<? extends PacketBase>>();
    private boolean isPostInitialized = false;

    public boolean registerPacket(Class<? extends PacketBase> packet) {
        if (this.packets.size() > 256) {
            return false;
        }
        if (this.packets.contains(packet)) {
            return false;
        }
        if (this.isPostInitialized) {
            return false;
        }
        this.packets.add(packet);
        return true;
    }

    @Override
    protected void encode(ChannelHandlerContext handlerContext, PacketBase packetBase, List<Object> out) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        Class<? extends PacketBase> packetClass = packetBase.getClass();
        if (!this.packets.contains(packetBase.getClass())) {
            throw new NullPointerException("Registry entry for " + packetBase.getClass().getCanonicalName() + " could not be located.");
        }
        byte diss = (byte)this.packets.indexOf(packetClass);
        buffer.writeByte(diss);
        packetBase.encodeInto(handlerContext, buffer);
        FMLProxyPacket proxyPacket = new FMLProxyPacket((PacketBuffer)buffer.copy(), handlerContext.channel().attr(NetworkRegistry.FML_CHANNEL).get());
        out.add(proxyPacket);
    }

    @Override
    protected void decode(ChannelHandlerContext handlerContext, FMLProxyPacket packet, List<Object> out) throws Exception {
        ByteBuf payload = packet.payload();
        byte diss = payload.readByte();
        Class<? extends PacketBase> packetClass = this.packets.get(diss);
        if (packetClass == null) {
            throw new NullPointerException("Packet registered for " + diss + " could not be located.");
        }
        PacketBase packetBase = packetClass.newInstance();
        packetBase.decodeInto(handlerContext, payload.slice());
        EntityPlayer player;
        switch (FMLCommonHandler.instance().getEffectiveSide()) {
            case CLIENT:
                player = this.getClientPlayer();
                packetBase.handleClientSide(player);
                break;

            case SERVER:
                INetHandler netHandler = handlerContext.channel().attr(NetworkRegistry.NET_HANDLER).get();
                player = ((NetHandlerPlayServer)netHandler).player;
                packetBase.handleServerSide(player);
                break;

            default:
        }
    }

    public void init() {
        this.channels = NetworkRegistry.INSTANCE.newChannel("CompressedBlocks", this);
    }

    public void postInit() {
        if (this.isPostInitialized) {
            return;
        }
        this.isPostInitialized = true;
        Collections.sort(this.packets, (packetClass1, packetClass2) -> {
            int com = String.CASE_INSENSITIVE_ORDER.compare(packetClass1.getCanonicalName(), packetClass2.getCanonicalName());
            if (com == 0) {
                com = packetClass1.getCanonicalName().compareTo(packetClass2.getCanonicalName());
            }
            return com;
        });
    }

    @SideOnly(Side.CLIENT)
    private EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }

    public static void sendToAll(PacketBase packetBase) {
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
        INSTANCE.channels.get(Side.SERVER).writeAndFlush(packetBase);
    }

    public static void sendTo(PacketBase packetBase, EntityPlayerMP playerMP) {
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(playerMP);
        INSTANCE.channels.get(Side.SERVER).writeAndFlush(packetBase);
    }

    public static void sendToAllAround(PacketBase packetBase, NetworkRegistry.TargetPoint target) {
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(target);
        INSTANCE.channels.get(Side.SERVER).writeAndFlush(packetBase);
    }

    public static void sendToAllAround(PacketBase packetBase, TileEntity tileEntity) {
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new NetworkRegistry.TargetPoint(tileEntity.getWorld().provider.getDimension(), tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), 192));
        INSTANCE.channels.get(Side.SERVER).writeAndFlush(packetBase);
    }

    public static void sendToAllAround(PacketBase packetBase, World world, int x, int y, int z) {
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(new NetworkRegistry.TargetPoint(world.provider.getDimension(), x, y, z, 192));
        INSTANCE.channels.get(Side.SERVER).writeAndFlush(packetBase);
    }

    public static void sendToDimension(PacketBase packetBase, int dimensionId) {
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        INSTANCE.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
        INSTANCE.channels.get(Side.SERVER).writeAndFlush(packetBase);
    }

    public static void sendToServer(PacketBase packetBase) {
        INSTANCE.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        INSTANCE.channels.get(Side.CLIENT).writeAndFlush(packetBase);
    }

    public static Packet toMCPacket(PacketBase packetBase) {
        return INSTANCE.channels.get(FMLCommonHandler.instance().getEffectiveSide()).generatePacketFrom(packetBase);
    }
}
