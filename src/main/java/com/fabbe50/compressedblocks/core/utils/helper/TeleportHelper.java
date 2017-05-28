package com.fabbe50.compressedblocks.core.utils.helper;

import com.fabbe50.compressedblocks.core.lib.Configs;
import com.thefifthidiot.tficore.utility.helper.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.util.Iterator;

/**
 * Created by fabbe50 on 10/03/2017.
 */
public class TeleportHelper {
    public static void afterTeleport(MinecraftServer minecraftServer, EntityPlayerMP playermp, WorldServer oldWorld, WorldServer newWorld, int oldDim, int dimension) {
        minecraftServer.getPlayerList().preparePlayer(playermp, oldWorld);
        playermp.interactionManager.setWorld(newWorld);
        minecraftServer.getPlayerList().updateTimeAndWeatherForPlayer(playermp, newWorld);
        minecraftServer.getPlayerList().syncPlayerInventory(playermp);
        Iterator<?> iterator = playermp.getActivePotionEffects().iterator();
        while (iterator.hasNext()) {
            PotionEffect potioneffect = (PotionEffect) iterator.next();
            playermp.connection.sendPacket(new SPacketEntityEffect(playermp.getEntityId(), potioneffect));
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(playermp, oldDim, dimension);
    }

    public static void teleport(World worldIn, EntityPlayer player, NBTTagCompound tags) {
        MinecraftServer minecraftServer = worldIn.getMinecraftServer();

        EntityPlayerMP playermp = (EntityPlayerMP)player;

        WorldServer oldWorld = null;
        WorldServer newWorld = null;
        int oldDim = 0;
        int dimension = 0;
        BlockPos blockPos = new BlockPos(tags.getDouble("posX"), tags.getDouble("posY"), tags.getDouble("posZ"));
        ChunkPos chunkPos = new ChunkPos(blockPos);
        if (Configs.enableCrossDimensionalTP) {
            oldWorld = minecraftServer.worldServerForDimension(playermp.dimension);
            newWorld = minecraftServer.worldServerForDimension(tags.getInteger("dimension"));
            oldDim = playermp.dimension;
            dimension = tags.getInteger("dimension");
        }

        boolean teleported = false;
        boolean cdTeleported = false;

        if (Configs.enableCrossDimensionalTP) {
            if (player.dimension != tags.getInteger("dimension")) {
                if (Configs.dimBlacklistR.contains(tags.getInteger("dimension")) || Configs.dimBlacklistR.contains(playermp.dimension)) {
                    if (Configs.dimBlacklistR.contains(playermp.dimension))
                        player.sendMessage(new TextComponentString("Cross-dimensional travel blacklisted from this dimension!"));
                    else if (Configs.dimBlacklistR.contains(tags.getInteger("dimension")))
                        player.sendMessage(new TextComponentString("Cross-dimensional travel blacklisted to this dimension!"));
                }
                else if (minecraftServer.getAllowNether() || tags.getInteger("dimension") != -1) {
                    player.sendStatusMessage(new TextComponentString("Teleporting..."), true);
                    player.changeDimension(tags.getInteger("dimension"));
                    cdTeleported = true;
                }
                else {
                    player.sendMessage(new TextComponentString("Couldn't perform teleportation"));
                }
            }
            else {
                player.sendStatusMessage(new TextComponentString("Teleporting..."), true);
                teleported = true;
            }
        }
        else {
            teleported = true;
        }

        if (teleported || cdTeleported) {
            LogHelper.info(playermp.getPosition());
            playermp.moveToBlockPosAndAngles(blockPos, player.getRotationYawHead(), player.rotationPitch);
            playermp.connection.setPlayerLocation(playermp.posX, playermp.posY, playermp.posZ, playermp.rotationYaw, playermp.rotationPitch);
            LogHelper.info(playermp.getPosition());
            LogHelper.info("Attempted to move player to position: " + tags.getDouble("posX") + " " + tags.getDouble("posY") + " " + tags.getDouble("posZ"));
        }

        if (Configs.enableCrossDimensionalTP && cdTeleported) {
            TeleportHelper.afterTeleport(minecraftServer, playermp, oldWorld, newWorld, oldDim, dimension);
        }

        if ((teleported || cdTeleported)) {
            player.sendStatusMessage(new TextComponentString("Teleported!"), true);
        }
    }
}