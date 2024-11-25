package com.favtuts.sbelasticsearchdemo.service;

import com.favtuts.sbelasticsearchdemo.model.Book;
import com.favtuts.sbelasticsearchdemo.exception.BookNotFoundException;
import com.favtuts.sbelasticsearchdemo.exception.DuplicateIsbnException;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getByIsbn(String isbn);

    List<Book> getAll();

    List<Book> findByAuthor(String authorName);

    List<Book> findByTitleAndAuthor(String title, String author);

    Book create(Book book) throws DuplicateIsbnException;

    void deleteById(String id);

    Book update(String id, Book book) throws BookNotFoundException;
}