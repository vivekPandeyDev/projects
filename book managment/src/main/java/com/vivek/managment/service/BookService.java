package com.vivek.managment.service;


import com.vivek.managment.dto.RequestBook;

import java.util.List;
import java.util.UUID;

public interface BookService {
     RequestBook addBook(RequestBook requestBook);
     RequestBook removeBook(UUID bookId);
     RequestBook updateBook(UUID bookId, RequestBook requestBook);
     RequestBook getBook(UUID bookId);
     List<RequestBook> getBooks();
}
