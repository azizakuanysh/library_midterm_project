package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // CREATE
    @PostMapping
    public Book create(@Valid @RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    // GET ALL
    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody BookDto dto) {
        return bookService.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}