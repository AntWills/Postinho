package com.project.model;

public class Doctor {
    private int id;
    private String name;

    public Doctor() {
    }

    public Doctor(String name) {
        this.name = name;
    }

    public Doctor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
