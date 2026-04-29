package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @NotNull
    private Boolean available;

    @NotNull
    private Long authorId;
}