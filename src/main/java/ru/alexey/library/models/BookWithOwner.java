package ru.alexey.library.models;

import java.util.Optional;

public class BookWithOwner {
    Book book;
    Optional<Person> person;

    public BookWithOwner(Book book, Optional<Person> person) {
        this.book = book;
        this.person = person;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Optional<Person> getPerson() {
        return person;
    }

    public void setPerson(Optional<Person> person) {
        this.person = person;
    }
}
