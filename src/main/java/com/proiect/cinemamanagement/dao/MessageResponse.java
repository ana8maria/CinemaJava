package com.proiect.cinemamanagement.dao;

import javax.validation.constraints.NotNull;

public class MessageResponse {
    @NotNull
    private String message ;

    public MessageResponse(@NotNull String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
