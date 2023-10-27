package com.pluralsight.menus;

import com.pluralsight.Transaction;
import com.pluralsight.utils.FileHelper;
import com.pluralsight.utils.PrintHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class LedgerMenu {
    private RecordsMenu recordsMenu;
    private Scanner scanner;
    private ArrayList<Transaction> transactions;

    public LedgerMenu(Scanner scanner, ArrayList<Transaction> transactions) {
        this.scanner = scanner;
        this.transactions = transactions;
        this.recordsMenu = new RecordsMenu(scanner, transactions);
    }

    public void startMenu() {
        boolean running = true;
        while (running) {
            System.out.println("""
                    =================================
                        LEDGER MENU
                    =================================
                    Please Select One of the Following Options:
                    A) All
                    D) Deposits
                    P) Payments
                    R) View Reports
                    H) Home Screen
                    """);

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A":
                    displayLedger();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    recordsMenu.startMenu();
                    break;
                case "H":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void displayLedger() {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByDate(transactionsCopy);
        PrintHelper.printAllHeader();


        for (Transaction transaction : transactionsCopy) {
            String type = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
            System.out.printf("%-20s%-15s%-20s%-20s%-15s%-10.2f\n", transaction.getDate(), transaction.getTime(), transaction.getDescription(),
                    transaction.getVendor(), type, transaction.getAmount());
        }

        PrintHelper.printClosingLine();
    }

    private void displayDeposits() {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByDate(transactionsCopy);
        PrintHelper.printHeader();

        for (Transaction transaction : transactionsCopy) {
            if (transaction.getAmount() >= 0) { // Check if the transaction is a deposit
                System.out.printf("%-20s%-15s%-20s%-20s%-10.2f\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }

        PrintHelper.printClosingLine();
    }


    private void displayPayments() {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByDate(transactionsCopy);
        PrintHelper.printHeader();

        for (Transaction transaction : transactionsCopy) {
            if (transaction.getAmount() < 0) { // Check if the transaction is a payment
                System.out.printf("%-20s%-15s%-20s%-20s%-10.2f\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
            }
        }

        PrintHelper.printClosingLine();
    }
}
