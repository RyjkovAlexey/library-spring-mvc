package ru.alexey.library.models;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 100, message = "Название книги не может быть короче двух и длиннее ста символов")
    private String name;

    @NotEmpty(message = "Автор не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя автора не может быть короче двух и длиннее ста символов")
    @Pattern(regexp = "[А-я]{2,100}", message = "Имя автора не может содерать цифр")
    private String author;

    @Min(value = 1000, message = "Год выхода книги не может быть меньше 1000г")
    private int year;

    private Integer ownerId;

    public Book() {
    }

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
