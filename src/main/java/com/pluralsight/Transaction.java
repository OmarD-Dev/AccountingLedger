package com.pluralsight;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private  String description;
    private String vendor;
    private Double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, Double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }


    public String getVendor() {
        return vendor;
    }

    public Double getAmount() {
        return amount;
    }
    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }
    //Created a LocalDateTime to make comparisons easier.
    public LocalDateTime dateTime(){
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return dateTime;
    }
    public String toTableFormat(String format){
        return String.format(
                format, date, time, description, vendor, amount
        );
    }

    @Override
    public String toString() {
        return  date +
                "|" + time +
                "|" + description +
                "|" + vendor +
                "|" + amount ;
    }
}