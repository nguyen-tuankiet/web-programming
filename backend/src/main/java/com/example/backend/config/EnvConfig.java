package com.example.backend.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.Map;
import java.util.HashMap;

public class EnvConfig {
    private static final Map<String, String> envVariables = new HashMap<>();
    private static final Dotenv dotenv;

    static {
        try {
            // Load .env file
            dotenv = Dotenv.configure()
//                    .directory(".")
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();

            // Copy all environment variables to our map
            dotenv.entries().forEach(entry ->
                envVariables.put(entry.getKey(), entry.getValue())
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load .env file", e);
        }
    }

    public static String get(String key) {
        // First try to get from environment variables
        String value = System.getenv(key);
        if (value != null) {
            return value;
        }
        
        // Then try to get from .env file
        return envVariables.get(key);
    }
}
