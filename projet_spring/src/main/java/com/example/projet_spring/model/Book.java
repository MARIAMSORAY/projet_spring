package com.example.projet_spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 1, max = 100)
    private String author;

    @Size(min = 10, max = 13)
    private String isbn;

    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        AVAILABLE,
        BORROWED
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Date getPublishedDate() { return publishedDate; }
    public void setPublishedDate(Date publishedDate) { this.publishedDate = publishedDate; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
