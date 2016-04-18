package com.fabbe50.compressedblocks.commands;

import com.fabbe50.compressedblocks.handler.AchievementHandler;
import com.fabbe50.compressedblocks.handler.ConfigurationHandler;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

/**
 * Created by fabbe50 on 22/03/2016.
 */
public class CBPingPong extends CommandBase {

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        if (0 == getRequiredPermissionLevel()) {
            return true;
        }
        else{
            return sender.canCommandSenderUseCommand(this.getRequiredPermissionLevel(), this.getCommandName());
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return ConfigurationHandler.pingcommandpermission;
    }

    @Override
    public String getCommandName()
    {
        return "ping";
        // The name of the command, the string the user has to type following a "/" to call the command
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "/ping [all|help]";
        // The string to show when the user types "/help X", X being the string from "getCommandName"
    }

    // Method called when the command is typed in
    @Override
    public void processCommand(ICommandSender icommandsender, String[] string)
    {
        if(icommandsender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)icommandsender;

            if(string.length > 0) {
                if(string[0].equalsIgnoreCase("all") || string[0].equalsIgnoreCase("a")) {
                    player.addChatMessage(new ChatComponentText("Pong! Your ping is: " + String.valueOf(((EntityPlayerMP)player).ping) + "ms"));
                }
                else if(string[0].equalsIgnoreCase("help") || string[0].equalsIgnoreCase("h") || string[0].equalsIgnoreCase("?")) {
                    player.addChatMessage(new ChatComponentText("/ping [all/a|help/h/?]"));
                }
                else if(string[0].equalsIgnoreCase("pong")) {
                    player.addChatMessage(new ChatComponentText("You can't respond to yourself, silly!"));
                    player.addStat(AchievementHandler.pingEgg.registerStat(), 1);
                }
                else {
                    player.addChatMessage(new ChatComponentText(ColorHelper.red + "\"" + string[0] + "\" is not a valid argument!"));
                    player.addChatMessage(new ChatComponentText(ColorHelper.red + "Use command like: \"/ping [all|help]\""));
                }
            }

            if(string.length == 0) {
                //player.addChatMessage(new ChatComponentText("<" + player.getDisplayName() + "> /ping"));
                player.addChatMessage(new ChatComponentText("Pong!"));
            }
        }
    }
}
