package com.fabbe50.compressedblocks.commands;

import com.fabbe50.compressedblocks.client.gui.ModGuiCorgicon;
import com.fabbe50.compressedblocks.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

/**
 * Created by fabbe50 on 13/04/2016.
 */
public class CBCorgicon extends CommandBase {

    @Override
    public String getCommandName() {
        return "corgicon";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List getCommandAliases() {
        return null;
    }

    @Override
    public String getCommandUsage (ICommandSender sender) {
        return "/corgicon";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] strings) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            World world = player.getEntityWorld();

            if (!world.isRemote) {
                int i;
                WorldServer worldserver = MinecraftServer.getServer().worldServers[1];

                sender.addChatMessage(new ChatComponentTranslation("commands.main.heading", new Object[0]));

                ChunkCoordinates c = sender.getPlayerCoordinates();
                int x = c.posX;
                int y = c.posY;
                int z = c.posZ;

                LogHelper.info("Attempting to open gui");
                //Minecraft.getMinecraft().displayGuiScreen(new ModGuiCorgicon(player));
                LogHelper.info("Done!");
            }
        }
    }
}
