package com.fabbe50.compressedblocks.common.entity;

import com.fabbe50.compressedblocks.core.reference.Textures;

/**
 * Created by fabbe50 on 24/02/2016.
 */
public enum EnumCorgiTypes {
    Normal(0, "normal", Textures.CORGI_FOLDER + "normal.png"),
    Super(1, "super", Textures.CORGI_FOLDER + "super.png"),
    Spy(2, "spy", Textures.CORGI_FOLDER + "spy.png"),
    Sun(3, "sun", Textures.CORGI_FOLDER + "sunglasses.png"),
    Suit(4, "suit", Textures.CORGI_FOLDER + "suit.png"),
    Pirate(5, "pirate", Textures.CORGI_FOLDER + "pirate.png"),
    Melon(6, "melon", Textures.CORGI_FOLDER + "melon.png"),
    Iron(7, "iron", Textures.CORGI_FOLDER + "ironman.png"),
    Fabbe50(8, "fabbe50", Textures.CORGI_FOLDER + "fabbe50.png"),
    Zirlian(9, "zirlian", Textures.CORGI_FOLDER + "zirlian.png");


    private int corgiID;
    private String corgiName;
    private String resourcePath;

    EnumCorgiTypes (int ID, String name, String resourcePath) {
        this.corgiID = ID;
        this.corgiName = name;
        this.resourcePath = resourcePath;
    }

    public int getCorgiID () {
        return corgiID;
    }

    public String getCorgiName () {
        return corgiName;
    }

    public String getResourceLocation (int i) { return resourcePath; }

    public static int count() {
        return values().length;
    }
}
