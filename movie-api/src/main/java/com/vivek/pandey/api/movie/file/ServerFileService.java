package com.vivek.pandey.api.movie.file;

import com.vivek.pandey.api.movie.exception.ServiceException;
import com.vivek.pandey.api.movie.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.vivek.pandey.api.movie.utility.BasicUtility.getFileExtension;

@Service
@Slf4j
public class ServerFileService implements FileService {
    @Value("${project.poster}")
    private String path;
    @Value("${project.fileFormat}")
    private String fileFormat;



    @Override
    public String uploadFile(String fileName, MultipartFile file) throws IOException {
        if(validFileName(fileName).isEmpty()){
            throw new ServiceException("invalid file name try adding alphabets or number","INVALID FILE NAME");
        }

        String fileNameWithExtension = validFileName(fileName) + getFileExtension(fileFormat);
        Path filePath = Path.of(path, fileNameWithExtension);
        //checking if file already exists
        if(Files.exists(filePath)){
            throw new ServiceException("file already exists with the given name. Try with different name!!!","FILE ALREADY EXISTS");
        }

        File serverFile = filePath.toFile();

        if (!serverFile.getParentFile().exists()) {
            boolean isDirCreated = serverFile.getParentFile().mkdirs();
            log.info("Is dir created: {}, at location: {}", isDirCreated, serverFile.getParentFile());
        }
        Files.copy(file.getInputStream(), filePath);

        return fileNameWithExtension;
    }

    @NotNull
    private static String validFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9.]", "").replaceAll("\\.[^.]+$", "");
    }

    @Override
    public InputStream getResourceFile(String name) throws FileNotFoundException {
        Path filePath = Path.of(path, name);
        return new FileInputStream(filePath.toFile());
    }


}
