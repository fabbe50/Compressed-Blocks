package com.fabbe50.compressedblocks.entities;

/**
 * Created by fabbe50 on 24/02/2016.
 */
public enum EnumCorgiTypes {
    Normal(0, "normal"),
    SuperHero(1, "super");

    private int corgiID;
    private String corgiName;

    EnumCorgiTypes (int ID, String name) {
        this.corgiID = ID;
        this.corgiName = name;

    }

    public int getCorgiID () {
        return corgiID;
    }

    public String getCorgiName () {
        return corgiName;
    }
}
