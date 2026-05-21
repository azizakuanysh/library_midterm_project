package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;


@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    // CREATE
    @PostMapping
    public Author create(@Valid @RequestBody Author author) {
        return authorRepository.save(author);
    }

    @Operation(summary = "Get all authors")
    @GetMapping
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Author getById(@PathVariable Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    }

    // UPDATE
    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody Author updated) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        author.setName(updated.getName());
        author.setNationality(updated.getNationality());

        return authorRepository.save(author);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        authorRepository.delete(author);
    }
}