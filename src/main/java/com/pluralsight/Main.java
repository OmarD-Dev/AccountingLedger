package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static List<Transaction> Transactions = new ArrayList<>();

    public static void main(String[] args) {
        boolean isRunning = true;


        while (isRunning) {
            System.out.println("""
                                        
                    Hello and Welcome To The Accounting Ledger. 
                    Please Choose From One of the Following Options:
                    D) Add Deposit
                    P) Make Payment
                    L) View Ledger Screen
                    X) Exit
                                        
                    Type In Your Command: 
                    """);
            String choice = scanner.nextLine().toUpperCase();
            switch (choice) {
                case "D":
                    addDeposit();
                    for (Transaction t : Transactions) {
                        System.out.println(t);
                    }
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    //ledgerScreen();
                    break;
                case "X":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please Enter a Valid Option");
            }


        }


    }

    private static void saveTransaction(Transaction transaction) {
        BufferedWriter buffWriter = null;
        try {
            buffWriter = new BufferedWriter(new FileWriter("transactions.csv", true));
            String transactionLine = transaction.toTableFormat("%s|%s|%s|%s|%.2f\n");
            buffWriter.write(String.format(transactionLine, transaction.description(), transaction.vendor(), transaction.amount()));

            System.out.println("Transaction saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save the transaction.");
            ;
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

    private static void addDeposit() {
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        Double amount = scanner.nextDouble();

        LocalDateTime currentDate = LocalDateTime.now();
        Transaction deposit = new Transaction(currentDate, description, vendor, amount);
        Transactions.add(deposit);
        saveTransaction(deposit);

    }

    private static void makePayment() {
        System.out.println("Enter description: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        Double amount = scanner.nextDouble() * -1;

        LocalDateTime currentDate = LocalDateTime.now();
        Transaction payment = new Transaction(currentDate, description, vendor, amount);
        Transactions.add(payment);
        saveTransaction(payment);

        //


    }

    public static void ledgerScreen() {
        //add scanner and boolean
        //add available options for ledger screen
        //use switch case like in home screen
        //
    }


}