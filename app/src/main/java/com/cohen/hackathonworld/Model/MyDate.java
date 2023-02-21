package com.cohen.hackathonworld.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.cohen.hackathonworld.Exception.DateFormatException;

import java.time.LocalDate;

public class MyDate {

    public int date = 99999999;

    public MyDate(){

    }

    public MyDate withDate(int date) throws DateFormatException {
        if(date > 1000000 && date < 31000000){
            int year = date % 10000;
            if(year >= 2023){
                int month = (date / 10000) % 100;
                if(month >= 1 && month <= 12){
                    int day = (date / 1000000);
                    if(day <= 31){
                        this.date = date;
                        return this;
                    }
                }
            }
        }
        throw new DateFormatException();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int CurrentDate() throws DateFormatException {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        int date = getCurrentDateInInteger(day, month, year);
        return date;
    }
    private int getCurrentDateInInteger(int day, int month, int year) {
        String dayString = String.format("%02d", day);
        String monthString = String.format("%02d", month);
        String yearString = Integer.toString(year);
        String dateString;
        if (yearString.length() == 4) {
            dateString = dayString + monthString + yearString;
        } else {
            dateString = dayString + monthString + yearString.substring(2);
        }
        return Integer.parseInt(dateString);
    }
}
