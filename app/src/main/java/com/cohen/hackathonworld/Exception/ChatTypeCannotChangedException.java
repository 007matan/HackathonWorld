package com.cohen.hackathonworld.Exception;

public class ChatTypeCannotChangedException extends Throwable {

    public String getMassage() {
        return "You cant change type of chat, you could try to open a new chat for your purpose";
    }
}
