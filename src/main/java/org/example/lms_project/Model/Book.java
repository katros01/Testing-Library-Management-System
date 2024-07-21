package org.example.lms_project.Model;

public class Book {
    private Long id;
    private String name;
    private int quantity;
    private String description;
    private String author;
    private String genre;
    public Book(Long id, String name, int quantity, String description, String author, String genre) {
        this.id = id;
        this.name=name;
        this.quantity = quantity;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, int quantity, String description, String author, String genre) {
        this.name=name;
        this.quantity = quantity;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}

