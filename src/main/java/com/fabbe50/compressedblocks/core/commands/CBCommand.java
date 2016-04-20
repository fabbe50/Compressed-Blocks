package com.fabbe50.compressedblocks.core.commands;

import com.fabbe50.compressedblocks.common.entity.mobspawning.SpawnMob;
import com.fabbe50.compressedblocks.common.entity.tamables.corgis.EntityCorgi;
import com.fabbe50.compressedblocks.utility.ColorHelper;
import com.fabbe50.compressedblocks.utility.LogHelper;
import com.fabbe50.compressedblocks.utility.MathHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 01/04/2016.
 */
public class CBCommand extends CommandBase{
    List<String> aliases = new ArrayList<String>();

    //Entities
    EntityLiving entityLiving;
    EntityCorgi corgi;

    @Override
    public String getCommandName() {
        addAliases();
        return "compressedblocks";
    }

    private void addAliases() {
        aliases.add("cb");
    }

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
        return 4;
    }

    @Override
    public List getCommandAliases()
    {
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/compressedblocks [spawn | worldinfo | help]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] strings) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)sender;
            World world = player.worldObj;
            double posx = player.posX;
            double posy = player.posY;
            double posz = player.posZ;

            if (strings.length > 0) {
                if (strings[0].equalsIgnoreCase("spawn")) {
                    if (strings.length > 1 && strings[1].equalsIgnoreCase("corgi")) {
                        corgi = new EntityCorgi(world);
                        if (strings.length > 2 && MathHelper.quickIsInt(strings[2])) {
                            if (strings.length > 3 && strings[3].equalsIgnoreCase("true")) {
                                if (strings.length > 4 && strings[4] != null && strings[5] != null && strings[6] != null) {
                                    try {
                                        posx = Double.parseDouble(strings[4]);
                                        posy = Double.parseDouble(strings[5]);
                                        posz = Double.parseDouble(strings[6]);


                                        if (true) {
                                            EntityCorgi entityCorgi = (EntityCorgi) SpawnMob.spawnEntity(world, posx + 0.5, posy, posz + 0.5, corgi);
                                            try { entityCorgi.setCorgiType(Integer.parseInt(strings[2])); } catch (Exception e) { LogHelper.error(e); }
                                            entityCorgi.applyTraits();
                                        }
                                    }
                                    catch (Exception e) {
                                        player.addChatMessage(new ChatComponentText("Error! Command use: /compressedblocks spawn <mobname> [type] [isTamed] [posX] [posY] [posZ]")
                                                .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                                    }
                                }
                                else if (strings.length == 4) {
                                    EntityCorgi entityCorgi = (EntityCorgi) SpawnMob.spawnEntity(world, posx + 0.5, posy, posz + 0.5, corgi);
                                    try { entityCorgi.setCorgiType(Integer.parseInt(strings[2])); } catch (Exception e) { LogHelper.error(e); }
                                    entityCorgi.applyTraits();
                                }
                                else {
                                    player.addChatMessage(new ChatComponentText("Error! Command use: /compressedblocks spawn <mobname> [type] [isTamed] [posX] [posY] [posZ]")
                                            .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                                }
                            }
                            else if (strings.length > 4 && strings.length > 3 && strings[3].equalsIgnoreCase("false")) {
                                if (strings[4] != null && strings[5] != null && strings[6] != null) {
                                    try {
                                        posx = Double.parseDouble(strings[4]);
                                        posy = Double.parseDouble(strings[5]);
                                        posz = Double.parseDouble(strings[6]);

                                        EntityCorgi entityCorgi = (EntityCorgi) SpawnMob.spawnEntity(world, posx, posy, posz, corgi);
                                        try { entityCorgi.setCorgiType(Integer.parseInt(strings[2])); } catch (Exception e) { LogHelper.error(e); }
                                        entityCorgi.canBeTamed = false;
                                        entityCorgi.applyTraits();
                                    }
                                    catch (Exception e) {
                                        player.addChatMessage(new ChatComponentText("Error! Command use: /compressedblocks spawn <mobname> [type] [isTamed] [posX] [posY] [posZ]")
                                                .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                                    }
                                }
                                else if (strings.length == 4) {
                                    EntityCorgi entityCorgi = (EntityCorgi) SpawnMob.spawnEntity(world, posx, posy, posz, corgi);
                                    try { entityCorgi.setCorgiType(Integer.parseInt(strings[2])); } catch (Exception e) { LogHelper.error(e); }
                                    entityCorgi.canBeTamed = false;
                                    entityCorgi.applyTraits();
                                }
                                else {
                                    player.addChatMessage(new ChatComponentText("Error! Command use: /compressedblocks spawn <mobname> [type] [isTamed] [posX] [posY] [posZ]")
                                            .setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                                }
                            }
                            else {
                                EntityCorgi entityCorgi = (EntityCorgi) SpawnMob.spawnEntity(world, posx, posy, posz, corgi);
                                try { entityCorgi.setCorgiType(Integer.parseInt(strings[2])); } catch (Exception e) { LogHelper.error(e); }
                                entityCorgi.applyTraits();
                            }
                        }
                        else {
                            Entity entity = SpawnMob.spawnEntity(world, posx + 0.5, posy, posz + 0.5, corgi);
                        }
                    }
                    else if (strings[0].equalsIgnoreCase("help")) {
                        player.addChatMessage(new ChatComponentText("/compressedblocks spawn [entity]"));
                    }
                    else {
                        player.addChatMessage(new ChatComponentText(ColorHelper.red + "You must specify entity to spawn!"));
                    }
                }
                else if (strings[0].equalsIgnoreCase("worldinfo")) {
                    String day = getDate(world);
                    String spawn = getWorldSpawn(world);
                    if (strings.length > 1 && strings[1].equalsIgnoreCase("all")) {
                        player.addChatMessage(new ChatComponentText("World Spawn: " + spawn));
                        player.addChatMessage(new ChatComponentText("Entities: " + FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().countEntities(EnumCreatureType.creature, true)));
                        player.addChatMessage(new ChatComponentText("Entities: " + FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().countEntities(EnumCreatureType.creature, false)));
                        player.addChatMessage(new ChatComponentText("Time: " + FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getWorldTime()));
                        player.addChatMessage(new ChatComponentText("Day: " + day));
                    }
                    else {
                        player.addChatMessage(new ChatComponentText("World Spawn: " + spawn));
                        player.addChatMessage(new ChatComponentText("Day: " + day));
                    }
                }
                else if (strings[0].equalsIgnoreCase("help")) {
                    if (strings.length > 1) {
                        if (strings[1].equalsIgnoreCase("spawn")) {
                            player.addChatMessage(new ChatComponentText("/cb spawn <mobname> [type] \"[isTamed]\" [posX] [posY] [posZ]"));
                            player.addChatMessage(new ChatComponentText("\"[isTamed]\" only works on tamable mobs"));
                        }
                        else if (strings[1].equalsIgnoreCase("worldinfo")) {
                            player.addChatMessage(new ChatComponentText("/cb worldinfo [all]"));
                        }
                        else if (strings[1].equalsIgnoreCase("help")) {
                            player.addChatMessage(new ChatComponentText("/cb help [subcommand]"));
                        }
                    }
                    else {
                        player.addChatMessage(new ChatComponentText("/compressedblocks [spawn | worldinfo | help]"));
                    }
                }
                else {
                    player.addChatMessage(new ChatComponentText("Invalid Command! Try /cb help").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                } //Only runs if player do /cb "invalid subcommand"
            }
            if (strings.length == 0) {
                player.addChatMessage(new ChatComponentText("Hi!"));
            }
        }
    }

    private static String getDate(World world) {
        final long time = world.getTotalWorldTime();
        final String day = String.format("%.1f", time / 24000.0);
        return day;
    }

    private static String getWorldSpawn(World world) {
        final String formatedSpawnCords = "[X: " + world.getSpawnPoint().posX + ", Y: " + world.getSpawnPoint().posY + ", Z: " + world.getSpawnPoint().posZ + "]";
        return formatedSpawnCords;
    }
}
