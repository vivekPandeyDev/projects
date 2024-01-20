package com.vivek.pandey.api.movie.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadFile(String fileName, MultipartFile file) throws IOException;
    InputStream getResourceFile(String fileName) throws FileNotFoundException;
}
