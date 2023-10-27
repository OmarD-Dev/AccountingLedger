package com.pluralsight;

import com.pluralsight.menus.HomeMenu;
import com.pluralsight.utils.FileHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class FinancialTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = FileHelper.loadTransactions();
        HomeMenu homeMenu = new HomeMenu(scanner, transactions);
        homeMenu.startMenu();
    }
}