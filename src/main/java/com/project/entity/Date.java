package com.project.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.project.exception.InvalidDateException;

public class Date {
    private LocalDate date;

    public Date() {
    }

    public Date(String dateString) throws InvalidDateException {
        try {
            this.date = LocalDate.parse(dateString); // Converte a string para LocalDate
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Data invalida! Use o formato AAAA-MM-DD.");
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String dateString) throws InvalidDateException {
        try {
            this.date = LocalDate.parse(dateString); // Converte a string para LocalDate
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Data invalida! Use o formato AAAA-MM-DD.");
        }
    }

    public void setCurrentData() {
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
