package com.vivek.pandey.api.movie.file;

import com.vivek.pandey.api.movie.config.FileFormat;
import com.vivek.pandey.api.movie.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @Value("${project.fileFormat}")
    private String fileFormat;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
        String fileName = fileService.uploadFile(file.getOriginalFilename(), file);
        return ResponseEntity.ok("file upload successfully with name: %s".formatted(fileName));
    }

    @GetMapping("/{fileName}")
    public void fileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        InputStream fileData = fileService.getResourceFile(fileName);
        response.setContentType(getFileFormat(fileFormat).getMimeType());
        StreamUtils.copy(fileData, response.getOutputStream());
    }

    private FileFormat getFileFormat(String format) {
        return switch (format) {
            case "jpeg" -> FileFormat.JPEG;
            default -> FileFormat.PNG;
        };
    }


}
