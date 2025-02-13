package com.project.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Date {
    private LocalDate data;

    public Date() {
    }

    public Date(String dateString) {
        try {
            this.data = LocalDate.parse(dateString); // Converte a string para LocalDate
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida! Use o formato AAAA-MM-DD.");
        }
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
