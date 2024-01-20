package com.vivek.managment.service;

import com.vivek.managment.dto.RequestAuthor;
import com.vivek.managment.dto.RequestBook;
import com.vivek.managment.entity.Author;
import com.vivek.managment.entity.Book;
import com.vivek.managment.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService{
    private final ModelMapper mapper;
    private final AuthorRepository authorRepository;
    @Override
    public RequestAuthor addAuthor(RequestAuthor requestAuthor) {
        var author = mapper.map(requestAuthor, Author.class);
        author = authorRepository.save(author);
        requestAuthor.setAuthorId(author.getAuthorId());
        return requestAuthor;
    }

    @Override
    public RequestAuthor removeAuthor(UUID authorId) {
        return null;
    }

    @Override
    public RequestAuthor updateAuthor(UUID authorId, RequestAuthor requestAuthor) {
        return null;
    }

    @Override
    public RequestAuthor getAuthor(UUID authorId) {
        return null;
    }

    @Override
    public List<RequestAuthor> getAuthors() {
        final var authors = authorRepository.findAll();
        return authors.stream().map(dbBook -> mapper.map(dbBook, RequestAuthor.class)).toList();
    }
}
