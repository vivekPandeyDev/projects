package com.vivek.managment.service;

import com.vivek.managment.dto.RequestAuthor;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    RequestAuthor addAuthor(RequestAuthor requestAuthor);
    RequestAuthor removeAuthor(UUID authorId);
    RequestAuthor updateAuthor(UUID authorId, RequestAuthor requestAuthor);
    RequestAuthor getAuthor(UUID authorId);
    List<RequestAuthor> getAuthors();
}
