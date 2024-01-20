package com.vivek.managment.service;

import com.vivek.managment.dto.RequestBook;
import com.vivek.managment.entity.Book;
import com.vivek.managment.exception.CustomException;
import com.vivek.managment.repository.AuthorRepository;
import com.vivek.managment.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final ModelMapper mapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public RequestBook addBook(RequestBook requestBook) {
        final var authorId = requestBook.getAuthorId();
        authorRepository.findById(authorId).orElseThrow(() -> new CustomException("no author found with given id", new ArrayList<>(), HttpStatus.NOT_FOUND));
        var book = mapper.map(requestBook, Book.class);
        book = bookRepository.save(book);
        requestBook.setBookId(book.getBookId());
        return requestBook;
    }

    @Override
    public RequestBook removeBook(UUID bookId) {
        final var bdBook = bookRepository.findById(bookId).orElseThrow(() -> new CustomException("no book found with given id", new ArrayList<>(), HttpStatus.NOT_FOUND));
        final var requestBook = mapper.map(bdBook, RequestBook.class);
        bookRepository.deleteById(bookId);
        return requestBook;
    }

    @Override
    public RequestBook updateBook(UUID bookId, RequestBook requestBook) {
        var dbBook = bookRepository.findById(bookId).orElseThrow(() -> new CustomException("cannot find  book", new ArrayList<>(), HttpStatus.NOT_MODIFIED));
        mapper.map(requestBook, dbBook);
        dbBook.setBookId(bookId);
        dbBook.setAuthor(dbBook.getAuthor());
        dbBook = bookRepository.save(dbBook);
        requestBook.setBookId(dbBook.getBookId());
        return requestBook;
    }

    @Override
    public RequestBook getBook(UUID bookId) {
        final var bdBook = bookRepository.findById(bookId).orElseThrow(() -> new CustomException("no book found with given id", new ArrayList<>(), HttpStatus.NOT_FOUND));
        return mapper.map(bdBook, RequestBook.class);
    }

    @Override
    public List<RequestBook> getBooks() {
        final var bdBooks = bookRepository.findAll();
        return bdBooks.stream().map(dbBook -> mapper.map(dbBook, RequestBook.class)).toList();
    }
}
