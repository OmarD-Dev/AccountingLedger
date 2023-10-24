package com.pluralsight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner= new Scanner(System.in);
    private static List<Transaction> Transactions= new ArrayList<>();
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
            switch (choice){
                case "D":
                    addDeposit();
                break;
                case "P":
                    //makePayment();
                    break;
                case "L":
                    //ledgerScreen();
                    break;
                case "X":
                    isRunning= false;
                    break;
                default:
                    System.out.println("Invalid choice. Please Enter a Valid Option");
            }


        }
    }
    public static void addDeposit(){
        System.out.println("Enter description: ");
        String description= scanner.nextLine();
        System.out.println("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        Double amount = scanner.nextDouble();

        LocalDateTime currentDate= LocalDateTime.now();
        Transaction deposit = new Transaction(currentDate, description,vendor,amount);
        Transactions.add(deposit);
    }
    public static void makePayment(){
        //prompt user for payment info
        //


    }
    public static void ledgerScreen(){
        //add scanner and boolean
        //add available options for ledger screen
        //use switch case like in home screen
        //
    }

}