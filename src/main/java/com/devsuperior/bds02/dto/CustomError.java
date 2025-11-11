package com.devsuperior.bds02.dto;

import java.time.LocalDate;

public class CustomError {
    private LocalDate timestamp;
    private int status;
    private String error;
    private String path;

    public CustomError(LocalDate timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
