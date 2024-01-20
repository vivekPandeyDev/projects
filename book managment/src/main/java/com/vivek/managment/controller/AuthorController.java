package com.vivek.managment.controller;

import com.vivek.managment.dto.RequestAuthor;
import com.vivek.managment.dto.RequestBook;
import com.vivek.managment.dto.ResponseData;
import com.vivek.managment.exception.CustomException;
import com.vivek.managment.repository.AuthorRepository;
import com.vivek.managment.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
    @PostMapping
    public ResponseEntity<ResponseData<Object>> addAuthor(@RequestBody @Valid RequestAuthor requestAuthor, BindingResult result) {
        if (result.hasErrors()) {
            final var errors = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new CustomException("cannot add a new author", errors, HttpStatus.BAD_REQUEST);
        }
        requestAuthor = authorService.addAuthor(requestAuthor);
        final var responseBody = ResponseData.builder().data(List.of(requestAuthor)).message("added a new author").status(HttpStatus.CREATED).build();
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Object>> getAuthors() {
        final var requestBooks = authorService.getAuthors();
        final var responseBody = ResponseData.builder().data(List.of(requestBooks)).message("list of all authors").status(HttpStatus.CREATED).build();
        return ResponseEntity.ok(responseBody);
    }

    // delete author code
}
