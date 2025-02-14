package com.project.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.project.exception.InvalidDateException;

public class Date {
    private LocalDate data;

    public Date() {
    }

    public Date(String dateString) throws InvalidDateException {
        try {
            this.data = LocalDate.parse(dateString); // Converte a string para LocalDate
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Data invalida! Use o formato AAAA-MM-DD.");
        }
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(String dateString) throws InvalidDateException {
        try {
            this.data = LocalDate.parse(dateString); // Converte a string para LocalDate
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Data invalida! Use o formato AAAA-MM-DD.");
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
