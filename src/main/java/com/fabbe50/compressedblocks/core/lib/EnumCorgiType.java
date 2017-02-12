package com.fabbe50.compressedblocks.core.lib;

import com.fabbe50.compressedblocks.core.reference.Reference;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe50 on 26/09/2016.
 */
public enum EnumCorgiType {
    NORMAL("normal", 0, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_normal.png")),
    FABBE50("fabbe50", 1, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_fabbe50.png")),
    ZIRLIAN("zirlian", 2, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_zirlian.png")),
    BODYGUARD("bodyguard", 3, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_bodyguard.png")),
    BUSINESS("business", 4, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_business.png")),
    MELON("melon", 5, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_melon.png")),
    PIRATE("pirate", 6, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_pirate.png")),
    SPY("spy", 7, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_spy.png")),
    SUNGLASSES("sunglasses", 8, new ResourceLocation(Reference.MOD_ID, Reference.TEXTURECORGIDIR + "corgi_sunglasses.png"));


    private static final EnumCorgiType[] DMG_LOOKUP = new EnumCorgiType[values().length];
    private final String name;
    private final int ID;
    private final ResourceLocation resourceLocation;

    EnumCorgiType(String name, int ID, ResourceLocation resourceLocation) {
        this.name = name;
        this.ID = ID;
        this.resourceLocation = resourceLocation;
    }

    public static EnumCorgiType byDamage(int damage) {
        if (damage < 0 || damage >= DMG_LOOKUP.length) {
            damage = 0;
        }

        return DMG_LOOKUP[damage];
    }

    public static EnumCorgiType byName(String name) {
        for (int i = 0; i < EnumCorgiType.count(); i++) {
            if (EnumCorgiType.byDamage(i).getName().equals(name.toLowerCase())) {
                return EnumCorgiType.byDamage(i);
            }
        }
        return null;
    }

    public String getName () {
        return name;
    }

    public int getID () {
        return ID;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public static int count() {
        return values().length;
    }

    static {
        for (EnumCorgiType enumCorgiType : values()) {
            DMG_LOOKUP[enumCorgiType.getID()] = enumCorgiType;
        }
    }
}
