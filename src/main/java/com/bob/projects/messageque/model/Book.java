package com.bob.projects.messageque.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Book.class)
@Data
@Builder
@NoArgsConstructor
public class Book implements Serializable {
    private String author;
    private String title;
    private String publisher;
    private String genre;

    public Book(String author, String title, String publisher, String genre) {
        this.author = author;
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
    }
}
