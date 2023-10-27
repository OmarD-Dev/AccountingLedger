package com.pluralsight.utils;

public class PrintHelper {
    // Create them as functions so all lines are uniform and code is cleaned up
    public static void printAllHeader() {
        System.out.println("=====================================================================================================");
        System.out.printf("%-20s%-15s%-20s%-20s%-20s%-10s\n", "Date", "Time", "Description", "Vendor", "Type", "Amount");
        System.out.println("=====================================================================================================");
    }

    public static void printHeader() {
        System.out.println("============================================================================================================");
        System.out.printf("%-20s%-15s%-20s%-20s%-10s\n", "Date", "Time", "Description", "Vendor", "Amount");
        System.out.println("============================================================================================================");
    }

    public static void printClosingLine() {
        System.out.println("=====================================================================================================");
    }

}

