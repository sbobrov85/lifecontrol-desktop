package com.jerait.lifecontrol.desktop.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Contains various tools.
 */
public final class Tools {
    /**
     * Close constructor for utility class.
     */
    private Tools() {
    }

    /**
     * Generate random 32-byte salt.
     * @return random salt.
     */
    public static String saltGenerate() {
        Random random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Translate string.
     * @param string string for translate.
     * @param resources language resources.
     * @return translated or original string.
     */
    public static String translateString(
        String string,
        final ResourceBundle resources
    ) {
        if (string.contains("%")) {
            string = resources.getString(
                string.replaceAll("%", "")
            );
        }

        return string;
    }
}
