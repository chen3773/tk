package com.tiktok.task.domain;

public class ErrorMessage {
    private String message;
    private String type;

    public ErrorMessage(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String generateMessage() {
        return "Type: " + type + ", Message: " + message;
    }
}

