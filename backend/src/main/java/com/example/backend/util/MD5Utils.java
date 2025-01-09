package com.example.backend.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String hash(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating MD5 hash", e);
        }
    }

    public static boolean verify(String inputPassword, String hashPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputPassword.getBytes());
        byte[] digest = md.digest();
        String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hashPassword.equals(myChecksum);
    }
}
