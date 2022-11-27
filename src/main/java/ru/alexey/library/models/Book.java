package ru.alexey.library.models;

import org.springframework.lang.Nullable;

public class Book {

    private int id;

    private String name;

    private String author;

    private int year;

    private Integer ownerId;

    public Book (){}

    public Book(int id, String name, String author, int year, int ownerId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.ownerId = ownerId;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
