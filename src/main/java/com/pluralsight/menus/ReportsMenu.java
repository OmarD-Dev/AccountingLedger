package com.pluralsight.menus;

import com.pluralsight.Transaction;
import com.pluralsight.utils.FileHelper;
import com.pluralsight.utils.PrintHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportsMenu {
    private Scanner scanner;
    private ArrayList<Transaction> transactions;

    public ReportsMenu(Scanner scanner, ArrayList<Transaction> transactions) {
        this.scanner = scanner;
        this.transactions = transactions;
    }

    public void startMenu() {
        boolean running = true;
        while (running) {
            System.out.println("""
                    =================================
                              REPORTS MENU
                    =================================
                    Choose an option:
                    1) Month To Date
                    2) Previous Month
                    3) Year To Date
                    4) Previous Year
                    5) Search by Vendor
                    6) Custom Search
                    0) Back
                                """);
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    monthToDateReports();
                    break;
                case "2":
                    previousMonthReports();
                    break;
                case "3":
                    currentYearReports();
                    break;

                case "4":
                    previousYearReports();
                    break;
                case "5":
                    System.out.println("Enter Vendor Name");
                    String vendorName = scanner.nextLine();
                    filterTransactionsByVendor(vendorName);
                    break;
                case "6":
                    customSearch();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    private void monthToDateReports() {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByDate(transactionsCopy);
        LocalDateTime currentDateTime = LocalDateTime.now();
        YearMonth currentYearMonth = YearMonth.from(currentDateTime);

        PrintHelper.printAllHeader();

        for (Transaction transaction : transactionsCopy) {
            LocalDateTime transactionDateTime = transaction.dateTime();
            YearMonth transactionYearMonth = YearMonth.from(transactionDateTime);

            // Check if the transaction is within the current month
            if (transactionYearMonth.equals(currentYearMonth) && transactionDateTime.getDayOfMonth() <= currentDateTime.getDayOfMonth()) {
                String type = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                System.out.printf("%-20s%-15s%-20s%-20s%-20s%-10.2f\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), type, transaction.getAmount());
            }
        }

        PrintHelper.printClosingLine();
    }

    private void previousMonthReports() {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByDate(transactionsCopy);
        LocalDateTime currentDateTime = LocalDateTime.now();
        YearMonth currentYearMonth = YearMonth.from(currentDateTime);
        YearMonth previousMonth = currentYearMonth.minusMonths(1);

        PrintHelper.printAllHeader();

        for (Transaction transaction : transactionsCopy) {
            LocalDateTime transactionDateTime = transaction.dateTime();
            YearMonth transactionYearMonth = YearMonth.from(transactionDateTime);

            if (transactionYearMonth.equals(previousMonth)) {
                String type = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                System.out.printf("%-20s%-15s%-20s%-20s%-20s%-10.2f\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), type, transaction.getAmount());
            }
        }

        PrintHelper.printClosingLine();
    }

    private void currentYearReports() {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByDate(transactionsCopy);
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentYear = currentDateTime.getYear();

        PrintHelper.printAllHeader();

        for (Transaction transaction : transactionsCopy) {
            LocalDateTime transactionDateTime = transaction.dateTime();
            int transactionYear = transactionDateTime.getYear();

            // Check if the transaction is within the current year
            if (transactionYear == currentYear) {
                String type = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                System.out.printf("%-20s%-15s%-20s%-20s%-20s%-10.2f\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), type, transaction.getAmount());
            }
        }

        PrintHelper.printClosingLine();
    }

    private void previousYearReports() {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByDate(transactionsCopy);
        LocalDateTime currentDateTime = LocalDateTime.now();
        int currentYear = currentDateTime.getYear();
        int previousYear = currentYear - 1;

        PrintHelper.printAllHeader();

        for (Transaction transaction : transactionsCopy) {
            LocalDateTime transactionDateTime = transaction.dateTime();
            int transactionYear = transactionDateTime.getYear();

            // Check if the transaction is within the previous year
            if (transactionYear == previousYear) {
                String type = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                System.out.printf("%-20s%-15s%-20s%-20s%-20s%-10.2f\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), type, transaction.getAmount());
            }
        }

        PrintHelper.printClosingLine();
    }

    private void filterTransactionsByVendor(String vendor) {
        ArrayList<Transaction> transactionsCopy = new ArrayList<>(transactions);
        FileHelper.sortByVendor(transactionsCopy);
        PrintHelper.printAllHeader();

        boolean found = false;
        for (Transaction transaction : transactionsCopy) {
            if (transaction.getVendor().contains(vendor)) {
                found = true;
                String type = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                System.out.printf("%-20s%-15s%-20s%-20s%-20s%-10.2f\n", transaction.getDate(), transaction.getTime(),
                        transaction.getDescription(), transaction.getVendor(), type, transaction.getAmount());
            }
        }

        if (!found) {
            System.out.println("No transactions found for the specified vendor: " + vendor);
        }

        PrintHelper.printClosingLine();
    }
    private void customSearch() {
        System.out.println("For a custom search please enter the following information or leave areas blank to not filter for them.");
        System.out.println("Search by start date (yyyy-MM-dd):");
        String startDate = scanner.nextLine();
        System.out.println("Search by end date (yyyy-MM-dd):");
        String endDate = scanner.nextLine();
        System.out.println("Search by description: ");
        String description = scanner.nextLine();
        System.out.println("Search by vendor:");
        String vendor = scanner.nextLine();
        System.out.println("Search by amount:");
        String amount = scanner.nextLine();

        // create a new empty array -> inserting the valid transactions
        ArrayList<Transaction> validTransactions = new ArrayList<>();
        FileHelper.sortByDate(validTransactions);
        for (Transaction transaction : transactions) {
            if (
                    isAfterStartDate(startDate, transaction.getDate()) &&
                            isBeforeEndDate(endDate, transaction.getDate()) &&
                            containsDescription(description, transaction.getDescription()) &&
                            isSameVendor(vendor, transaction.getVendor()) &&
                            isSameAmount(amount, transaction.getAmount())
            ) {
                validTransactions.add(transaction);
            }
        }

        PrintHelper.printAllHeader();

        for (Transaction t : validTransactions) {
            String type = t.getAmount() >= 0 ? "Deposit" : "Payment";
            System.out.printf("%-20s%-15s%-20s%-20s%-15s%-10.2f\n", t.getDate(), t.getTime(), t.getDescription(),
                    t.getVendor(), type, t.getAmount());
        }
        PrintHelper.printClosingLine();
    }
    // Create methods that return booleans to see if a field should be filtered or not
    private boolean isAfterStartDate(String inputStartDate, LocalDate transactionDate) {
        if (inputStartDate.equals("")) return true;
        LocalDate inputAsLocalDate = LocalDate.parse(inputStartDate);
        return transactionDate.compareTo(inputAsLocalDate) > 0;

    }

    private boolean isBeforeEndDate(String inputEndDate, LocalDate transactionDate) {
        if (inputEndDate.equals("")) return true;
        LocalDate inputAsLocalDate = LocalDate.parse(inputEndDate);
        return transactionDate.compareTo(inputAsLocalDate) < 0;
    }

    private boolean containsDescription(String inputDescription, String transactionDescription) {
        return transactionDescription.contains(inputDescription);
    }

    private boolean isSameVendor(String inputVendor, String transactionVendor) {
        return inputVendor.equals("") || inputVendor.equals(transactionVendor);
    }

    private boolean isSameAmount(String inputAmount, double transactionAmount) {
        return inputAmount.equals("") || transactionAmount == Double.parseDouble(inputAmount);
    }


}

