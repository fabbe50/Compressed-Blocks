package com.fabbe50.compressedblocks.core.utils.helper;

import net.minecraft.command.CommandException;
import net.minecraft.command.EntityNotFoundException;
import net.minecraft.command.EntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

/**
 * Created by fabbe50 on 30/04/2017.
 */
public class EntityHelper {
    public static Entity getEntity(MinecraftServer server, EntityPlayer player, String target) throws EntityNotFoundException, CommandException {
        return getEntity(server, player, target, Entity.class);
    }

    public static <T extends Entity> T getEntity(MinecraftServer server, EntityPlayer player, String target, Class <? extends T > targetClass) throws EntityNotFoundException, CommandException {
        Entity entity = EntitySelector.matchOneEntity(player, target, targetClass);

        if (entity == null) {
            entity = server.getPlayerList().getPlayerByUsername(target);
        }

        if (entity == null) {
            try {
                UUID uuid = UUID.fromString(target);
                entity = server.getEntityFromUuid(uuid);

                if (entity == null) {
                    entity = server.getPlayerList().getPlayerByUUID(uuid);
                }
            }
            catch (IllegalArgumentException var6) {
                if (target.split("-").length == 5) {
                    throw new EntityNotFoundException("commands.generic.entity.invalidUuid", new Object[] {target});
                }
            }
        }

        if (entity != null && targetClass.isAssignableFrom(entity.getClass())) {
            return (T)entity;
        }
        else {
            throw new EntityNotFoundException(target);
        }
    }
}
