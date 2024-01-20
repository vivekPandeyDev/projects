package com.vivek.pandey.api.movie.service;

import com.vivek.pandey.api.movie.movie.MovieDto;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    byte[] createExcelFile(List<MovieDto> movies) throws IOException;
}
