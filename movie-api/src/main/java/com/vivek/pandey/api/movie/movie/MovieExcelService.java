package com.vivek.pandey.api.movie.movie;

import com.vivek.pandey.api.movie.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class MovieExcelService implements ExcelService {
    @Override
    public byte[] createExcelFile(List<MovieDto> movies) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Movies");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Movie ID");
        headerRow.createCell(1).setCellValue("Title");
        headerRow.createCell(2).setCellValue("Director");
        headerRow.createCell(3).setCellValue("Studio");
        headerRow.createCell(4).setCellValue("Movie Cast");
        headerRow.createCell(5).setCellValue("Release Year");
        headerRow.createCell(6).setCellValue("Poster Name");
        headerRow.createCell(7).setCellValue("Poster URL");

        // Fill data rows
        int rowNum = 1;
        for (MovieDto movie : movies) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(movie.getMovieId());
            row.createCell(1).setCellValue(movie.getTitle());
            row.createCell(2).setCellValue(movie.getDirector());
            row.createCell(3).setCellValue(movie.getStudio());
            row.createCell(4).setCellValue(String.join(", ", movie.getMovieCast()));
            row.createCell(5).setCellValue(movie.getReleaseYear());
            row.createCell(6).setCellValue(movie.getPosterName());
            row.createCell(7).setCellValue(movie.getPosterUrl());
        }

        // Write workbook to byte array
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } finally {
            workbook.close();
        }
    }
}
