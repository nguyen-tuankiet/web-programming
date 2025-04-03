//package com.example.backend.config;
//
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.annotation.WebListener;
//
//@WebListener
//public class AppInitializer implements ServletContextListener {
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        try {
//            System.out.println("Initializing application...");
//
//            // Initialize environment variables
//            EnvConfig.initialize();
//            System.out.println("Environment variables loaded successfully");
//
//            System.out.println("Application initialized successfully");
//        } catch (Exception e) {
//            System.err.println("Error during application initialization: " + e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Failed to initialize application", e);
//        }
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        try {
//            System.out.println("Application shutting down...");
//            // Add any cleanup code here
//            System.out.println("Application shutdown complete");
//        } catch (Exception e) {
//            System.err.println("Error during application shutdown: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}