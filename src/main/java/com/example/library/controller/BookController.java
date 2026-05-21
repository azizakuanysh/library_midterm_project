package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title) {
        return bookService.searchByTitle(title);
    }
    @GetMapping("/filter")
    public List<Book> filterBooks(@RequestParam boolean available) {
        return bookService.filterByAvailability(available);
    }
    // CREATE
    @PostMapping
    public Book create(@Valid @RequestBody BookDto dto) {
        return bookService.create(dto);
    }
    @Operation(summary = "Get all books")
    @GetMapping
    public Page<Book> getAll(Pageable pageable) {
        return bookService.getAll(pageable);
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
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        try {

            Path uploadPath = Paths.get("uploads");

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(file.getOriginalFilename());

            Files.copy(file.getInputStream(), filePath);

            return "File uploaded successfully: " + file.getOriginalFilename();

        } catch (Exception e) {
            return "File upload failed";
        }
    }
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        try {

            Path filePath = Paths.get("uploads").resolve(filename);

            Resource resource = new UrlResource(filePath.toUri());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}