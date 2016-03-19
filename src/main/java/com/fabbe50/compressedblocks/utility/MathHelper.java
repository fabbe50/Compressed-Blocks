package com.fabbe50.compressedblocks.utility;

import com.fabbe50.compressedblocks.handler.ConfigurationHandler;

/**
 * Created by fabbe50 on 08/03/2016.
 */
public class MathHelper {
    //Util Variables
    private static int swordbase = 4;

    //Math Operations
    public static int endgamiumbasedamage = ConfigurationHandler.endgamiumdamagevalue - swordbase;
    public static int endgamiumextradamage = ConfigurationHandler.endgamiumextradamage - ConfigurationHandler.endgamiumdamagevalue;
}
