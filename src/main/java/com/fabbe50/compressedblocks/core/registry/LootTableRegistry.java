package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.core.reference.Reference;
import com.google.common.collect.Sets;
import net.minecraft.util.ResourceLocation;

import java.util.Set;

/**
 * Created by fabbe on 28/05/2017.
 */
public class LootTableRegistry {
    private static final Set<ResourceLocation> LOOT_TABLES = Sets.<ResourceLocation>newHashSet();
    public static final ResourceLocation ENTITIES_SQUID_WHITE = register("entities/squid/white");
    public static final ResourceLocation ENTITIES_SQUID_ORANGE = register("entities/squid/orange");
    public static final ResourceLocation ENTITIES_SQUID_MAGENTA = register("entities/squid/magenta");
    public static final ResourceLocation ENTITIES_SQUID_LIGHT_BLUE = register("entities/squid/light_blue");
    public static final ResourceLocation ENTITIES_SQUID_YELLOW = register("entities/squid/yellow");
    public static final ResourceLocation ENTITIES_SQUID_LIME = register("entities/squid/lime");
    public static final ResourceLocation ENTITIES_SQUID_PINK = register("entities/squid/pink");
    public static final ResourceLocation ENTITIES_SQUID_GRAY = register("entities/squid/gray");
    public static final ResourceLocation ENTITIES_SQUID_SILVER = register("entities/squid/silver");
    public static final ResourceLocation ENTITIES_SQUID_CYAN = register("entities/squid/cyan");
    public static final ResourceLocation ENTITIES_SQUID_PURPLE = register("entities/squid/purple");
    public static final ResourceLocation ENTITIES_SQUID_BLUE = register("entities/squid/blue");
    public static final ResourceLocation ENTITIES_SQUID_BROWN = register("entities/squid/brown");
    public static final ResourceLocation ENTITIES_SQUID_GREEN = register("entities/squid/green");
    public static final ResourceLocation ENTITIES_SQUID_RED = register("entities/squid/red");
    public static final ResourceLocation ENTITIES_SQUID_BLACK = register("entities/squid/black");

    private static ResourceLocation register(String id) {
        return register(new ResourceLocation(Reference.MOD_ID, id));
    }

    public static ResourceLocation register(ResourceLocation id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        }
        else {
            throw new IllegalArgumentException(id + " is already a registered built-in loot table");
        }
    }
}
