package com.pluralsight.menus;

import com.pluralsight.Transaction;
import com.pluralsight.utils.FileHelper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class HomeMenu {
    private Scanner scanner;
    private ArrayList<Transaction> transactions;

    public HomeMenu(Scanner scanner, ArrayList<Transaction> transactions) {
        this.scanner = scanner;
        this.transactions = transactions;
    }

    public void startMenu() {
        LedgerMenu ledgerMenu = new LedgerMenu(scanner, transactions);
        boolean running = true;
        while (running) {
            System.out.println(""" 
                    ==========================================
                          Welcome to The Accounting Ledger
                    ==========================================      
                    Please Select One of the Following Options:
                    D) Add Deposit
                    P) Make Payment
                    L) View Ledger
                    X) Exit
                    Type In Your Command: 
                            """
            );


            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    addPayment(scanner);
                    break;
                case "L":
                    ledgerMenu.startMenu();
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void addDeposit(Scanner scanner) {
        System.out.println("Enter date and time (yyyy-MM-dd) or leave empty for current date");
        String date = scanner.nextLine();
        LocalDate currentDate = null;
        if (date.isEmpty()) {
            currentDate = LocalDate.now();
        } else {
            try {
                currentDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format.");
            }
        }
        System.out.println("Enter time (HH:mm:ss) or leave empty for current time");
        String time = scanner.nextLine();
        LocalTime currentTime = null;
        if (time.isEmpty()) {
            currentTime = LocalTime.now();
        } else {
            try {
                currentTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format.");
            }
        }
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        Double amount = scanner.nextDouble();
        scanner.nextLine();
        Transaction deposit = new Transaction(currentDate, currentTime, description, vendor, amount);
        transactions.add(deposit);
        saveTransaction(FileHelper.FILE_NAME, deposit);

    }


    private void addPayment(Scanner scanner) {
        System.out.println("Enter date and time (yyyy-MM-dd) or leave empty for current date");
        String date = scanner.nextLine();
        LocalDate currentDate = null;
        if (date.isEmpty()) {
            currentDate = LocalDate.now();
        } else {
            try {
                currentDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format.");
            }
        }
        System.out.println("Enter time (HH:mm:ss) or leave empty for current time");
        String time = scanner.nextLine();
        LocalTime currentTime = null;
        if (time.isEmpty()) {
            currentTime = LocalTime.now();
        } else {
            try {
                currentTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format.");
            }
        }
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        Double amount = scanner.nextDouble() * -1;
        scanner.nextLine();

        Transaction payment = new Transaction(currentDate, currentTime, description, vendor, amount);
        transactions.add(payment);
        saveTransaction(FileHelper.FILE_NAME, payment);
    }

    private static void saveTransaction(String FILE_NAME, Transaction transaction) {
        BufferedWriter buffWriter = null;
        try {
            buffWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));
            String transactionLine = transaction.toTableFormat("%s|%s|%s|%s|%.2f\n");
            buffWriter.write(String.format(transactionLine, transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));

            System.out.println("Transaction saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save the transaction.");
        } finally {
            try {
                if (buffWriter != null) {
                    buffWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
