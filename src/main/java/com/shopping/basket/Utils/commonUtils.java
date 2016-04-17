package com.shopping.basket.Utils;

import java.util.Random;

/**
 * Common utility methods.
 */
public class commonUtils {

    /**
     * Utility function to generate unique ids
     */
    public String generateUniqueId(){
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        StringBuilder str = new StringBuilder();

        for(int i=0; i<5; i++){
            int index = rand.nextInt(25);
            Character ch = alphabets.charAt(index);
            str.append(ch);
        }

        return str.toString();
    }
}
