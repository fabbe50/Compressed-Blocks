package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.potions.PotionFlight;
import com.fabbe50.compressedblocks.common.potions.PotionMagnet;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.fabbe50.compressedblocks.core.utils.helper.LogHelper;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Created by fabbe on 28/11/2017 - 9:16 PM.
 */
public class PotionRegistry {
    public static Potion POTION_MAGNET = new PotionMagnet(false, 0x00ff00).setRegistryName(Reference.MOD_ID + ".magnet").setBeneficial().setPotionName("effect." + Reference.MOD_ID + ".magnet");
    public static Potion POTION_FLIGHT = new PotionFlight(false, 0xff0077).setRegistryName(Reference.MOD_ID + ".flight").setBeneficial().setPotionName("effect." + Reference.MOD_ID + ".flight");

    public static PotionType POTIONTYPE_MAGNET = new PotionType(new PotionEffect(POTION_MAGNET, 3600)).setRegistryName(Reference.MOD_ID + ".magnet");
    public static PotionType POTIONTYPE_MAGNET_EXTENDED = new PotionType(Reference.MOD_ID + ".magnet", new PotionEffect(POTION_MAGNET, 9600)).setRegistryName(Reference.MOD_ID, ".magnet.extended");
    public static PotionType POTIONTYPE_FLIGHT = new PotionType(new PotionEffect(POTION_FLIGHT, 3600)).setRegistryName(Reference.MOD_ID + ".flight");
    public static PotionType POTIONTYPE_FLIGHT_EXTENDED = new PotionType(Reference.MOD_ID + ".flight", new PotionEffect(POTION_FLIGHT, 9600)).setRegistryName(Reference.MOD_ID, ".flight.extended");

    public static void init() {
        registerPotionType(POTIONTYPE_MAGNET);
        registerPotionType(POTIONTYPE_MAGNET_EXTENDED);
        registerPotionType(POTIONTYPE_FLIGHT);
        registerPotionType(POTIONTYPE_FLIGHT_EXTENDED);
    }

    public static void subInit() {
        registerPotion(POTION_MAGNET);
        registerPotion(POTION_FLIGHT);
    }

    private static void registerPotionType(PotionType type) {
        ForgeRegistries.POTION_TYPES.register(type);
    }

    private static void registerPotion(Potion potion) {
        ForgeRegistries.POTIONS.register(potion);
    }
}
