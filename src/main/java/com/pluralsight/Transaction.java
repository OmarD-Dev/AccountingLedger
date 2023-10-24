package com.pluralsight;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Transaction(LocalDateTime dateTime, String description, String vendor, double amount ) {
    private static final DateTimeFormatter stringFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getDate(){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        return dateTime.format(formatter);
    }

    public String getTime(){
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("HH:mm:ss");
        return dateTime.format(formatter);
    }
    public String toTableFormat(String format){
        return String.format(
                format, getDate(), getTime(), description(),vendor(),amount()
        );
    }

    @Override
    public String toString() {
        return  dateTime.format(stringFormat) +
                "|" + description +
                "|" + vendor +
                "|" + amount ;
    }
}