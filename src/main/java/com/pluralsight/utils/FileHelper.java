package com.pluralsight.utils;

import com.pluralsight.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class FileHelper {
    public static final String FILE_NAME = "transactions.csv";

    //Reads transactions.csv to load up transactions into the ArrayList
    public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0].trim());
                    LocalTime time = LocalTime.parse(parts[1].trim());
                    String description = parts[2].trim();
                    String vendor = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);

                } else {
                    System.out.println("Invalid transaction format: " + line);
                }
            }
            reader.close();
            return transactions;
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
    //Sorts Array list by comparing a transactions' dateTime
    public static void sortByDate(ArrayList<Transaction> transactionsCopy) {
        Collections.sort(transactionsCopy, (t1, t2) -> {
            return t2.dateTime().compareTo(t1.dateTime());
        });
    }

    public static void sortByVendor(ArrayList<Transaction> transactionsCopy) {
        Collections.sort(transactionsCopy, (t1, t2) -> {
            return t1.getVendor().compareTo(t2.getVendor());
        });
    }

}
