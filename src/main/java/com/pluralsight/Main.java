package com.pluralsight;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
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
                    //addDeposit();
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
        // prompt user for transaction info
        // get user input for description, vendor, and amount
        // put data into new transaction object
        // save transaction to file
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