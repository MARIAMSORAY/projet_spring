package com.example.projet_spring.repository;

import com.example.projet_spring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
