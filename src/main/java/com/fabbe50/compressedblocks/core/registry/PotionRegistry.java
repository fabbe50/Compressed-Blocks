package com.fabbe50.compressedblocks.core.registry;

import com.fabbe50.compressedblocks.common.potions.PotionCustom;
import com.fabbe50.compressedblocks.core.reference.Reference;
import com.thefifthidiot.tficore.utility.LogHelper;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespacedDefaultedByKey;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by fabbe on 28/11/2017 - 9:16 PM.
 */
public class PotionRegistry {
    public static Potion POTION_MAGNET = new PotionCustom(false, 0x00ff00).setRegistryName(Reference.MOD_ID, "magnetization").setBeneficial().setPotionName("Magnetization");

    public static PotionEffect POTIONEFFECT_MAGNET = new PotionEffect(POTION_MAGNET, 3600);

    public static void init() {
        registerPotionType(POTION_MAGNET, POTIONEFFECT_MAGNET, "magnetization", true);
    }

    public static void subInit() {
        registerPotion(POTION_MAGNET, "magnetization");
    }

    private static void registerPotionType(Potion potion, PotionEffect effect, String name, boolean extend) {
        PotionType.REGISTRY.register(checkIDPotionType(new PotionType(effect)), new ResourceLocation(Reference.MOD_ID, name), new PotionType(effect));
        if (extend) {
            PotionType.REGISTRY.register(checkIDPotionType(new PotionType(effect + "extended")), new ResourceLocation(Reference.MOD_ID, name + "extended"), new PotionType(effect.getEffectName().toLowerCase(), new PotionEffect[] {new PotionEffect(potion, 9600)}));
        }
    }

    private static void registerPotion(Potion potion, String name) {
        Potion.REGISTRY.register(checkIDPotion(potion), new ResourceLocation(Reference.MOD_ID, name), potion);
    }

    private static int checkIDPotion(Potion potion) {
        int highestValue = 0;
        for (Potion p : Potion.REGISTRY) {
            highestValue = Potion.REGISTRY.getIDForObject(p);
        }
        LogHelper.info("Registered potion " + potion + " with id: " + highestValue);
        return highestValue + 1;
    }

    private static int checkIDPotionType(PotionType potion) {
        int highestValue = 0;
        for (PotionType p : PotionType.REGISTRY) {
            highestValue = PotionType.REGISTRY.getIDForObject(p);
        }
        LogHelper.info("Registered potiontype " + potion + " with id: " + highestValue);
        return highestValue + 1;
    }
}
