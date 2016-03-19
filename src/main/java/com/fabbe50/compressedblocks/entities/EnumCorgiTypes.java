package com.fabbe50.compressedblocks.entities;

import com.fabbe50.compressedblocks.reference.Textures;
import net.minecraft.util.ResourceLocation;

/**
 * Created by fabbe50 on 24/02/2016.
 */
public enum EnumCorgiTypes {
    Normal(0, "normal", new ResourceLocation(Textures.corgiPath + "normal.png")),
    Super(1, "super", new ResourceLocation(Textures.corgiPath + "super.png")),
    Spy(2, "spy", new ResourceLocation(Textures.corgiPath + "spy.png")),
    Sun(3, "sun", new ResourceLocation(Textures.corgiPath + "sunglasses.png")),
    Suit(4, "suit", new ResourceLocation(Textures.corgiPath + "suit.png")),
    Pirate(5, "pirate", new ResourceLocation(Textures.corgiPath + "pirate.png")),
    Melon(6, "melon", new ResourceLocation(Textures.corgiPath + "melon.png")),
    Iron(7, "iron", new ResourceLocation(Textures.corgiPath + "iron.png")),
    Fabbe50(8, "fabbe50", new ResourceLocation(Textures.corgiPath + "fabbe50.png")),
    Zirlian(9, "zirlian", new ResourceLocation(Textures.corgiPath + "zirlian.png"));


    private int corgiID;
    private String corgiName;
    private ResourceLocation resourceLocation;

    EnumCorgiTypes (int ID, String name, ResourceLocation resourceLocation) {
        this.corgiID = ID;
        this.corgiName = name;
        this.resourceLocation = resourceLocation;
    }

    public int getCorgiID () {
        return corgiID;
    }

    public String getCorgiName () {
        return corgiName;
    }

    public ResourceLocation getResourceLocation (int i) { return resourceLocation; }

    public static int count() {
        return values().length;
    }
}
