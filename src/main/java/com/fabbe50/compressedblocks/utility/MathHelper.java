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

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public static boolean quickIsInt(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) { //Not a valid number
            return false;
        } catch(NullPointerException e) { //String is empty
            return false;
        }
        //Only gets this far if we don't return false
        return true;
    }
}
