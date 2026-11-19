package com.college.library.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.library.model.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {

    // In-memory storage: data resets whenever the application restarts.
    private final List<Book> books = new ArrayList<>();

    // Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        boolean idAlreadyExists = books.stream()
                .anyMatch(existingBook -> existingBook.getId().equals(book.getId()));

        if (idAlreadyExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    // Retrieve all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }

    // Retrieve a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = findBook(id);
        return book.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update all details of an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @RequestBody Book updatedBook) {

        Optional<Book> existingBook = findBook(id);

        if (existingBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book book = existingBook.get();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        book.setAvailableCopies(updatedBook.getAvailableCopies());

        return ResponseEntity.ok(book);
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        Optional<Book> book = findBook(id);

        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        books.remove(book.get());
        return ResponseEntity.noContent().build();
    }

    private Optional<Book> findBook(Integer id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }
}
