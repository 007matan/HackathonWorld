package com.cohen.hackathonworld.Exception;

public class DateFormatException extends Throwable {

    public String getMassage() {
        return "Date format not recognize - DDMMYYYY";
    }
}
