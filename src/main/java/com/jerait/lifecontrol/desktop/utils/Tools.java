package com.jerait.lifecontrol.desktop.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

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
}
