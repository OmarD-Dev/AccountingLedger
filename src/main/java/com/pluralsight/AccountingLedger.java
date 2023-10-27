package com.pluralsight;

import com.pluralsight.menus.HomeMenu;
import com.pluralsight.utils.FileHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedger {
    //Start our application here and pass in Scanner and ArrayList to the Menus
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Transaction> transactions = FileHelper.loadTransactions();
        HomeMenu homeMenu = new HomeMenu(scanner, transactions);
        homeMenu.startMenu();
    }
}