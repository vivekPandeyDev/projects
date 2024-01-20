package com.example.soapfileservice.service;

import com.example.soapfileservice.dto.AddContentStreamRequest;
import com.example.soapfileservice.dto.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileDocumentService {

    private static final Logger logger = LoggerFactory.getLogger(FileDocumentService.class);
    @Value("${storage.path}")
    private String location;


    public String storeFileAndGetObjectId(AddContentStreamRequest request) {
        String objectId = "idd_" + UUID.randomUUID().toString();
        String base64EncodedData = request.getContentStream().getStream().getInclude();

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedData);
            File outputFile = new File(location, objectId);
            // Write the decoded data to the file
            saveFile(decodedBytes, outputFile);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid base64 data: {}", e.getMessage());
        }
        return objectId;
    }

    private static void saveFile(byte[] decodedBytes, File outputFile) {
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(decodedBytes);
            logger.info("file save successfully.");
        } catch (IOException e) {
            logger.error("Error writing the file: {}", e.getMessage());
        }
    }


    public Image getImage(String fileName) throws IOException {
        Path filePath = Paths.get(location, fileName).toAbsolutePath();
        if (Files.exists(filePath)) {
            logger.info("reading image from the location -> {}", filePath.toAbsolutePath().toAbsolutePath());
            Image image = new Image();
            final BufferedImage read = ImageIO.read(new File(filePath.toAbsolutePath().toString()));
            image.setContent(read);
            image.setName(fileName);
            return image;
        } else {
            logger.info("File not found");
            throw new IOException("File not found");
        }
    }
}

