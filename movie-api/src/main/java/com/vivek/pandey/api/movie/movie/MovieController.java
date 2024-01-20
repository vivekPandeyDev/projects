package com.vivek.pandey.api.movie.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivek.pandey.api.movie.exception.ServiceException;
import com.vivek.pandey.api.movie.service.DatabaseService;
import com.vivek.pandey.api.movie.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final DatabaseService<MovieDto,Integer> movieService;
    private final Validator validator;
    private final ExcelService excelService;

    @GetMapping("/all")
    public List<MovieDto> getAllMovies() {
        return movieService.getAllEntity();
    }

    @GetMapping
    public Page<MovieDto> paginatedMovie(@RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         @RequestParam(defaultValue = "title") String sortBy
                                         ){
        return movieService.getEntityWithPaginatedAndSorted(page,size,sortBy);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getSingleMovie(@PathVariable Integer movieId) {
        MovieDto movieDto = movieService.getEntity(movieId);
        return ResponseEntity.ok(movieDto);
    }


    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadMovies(HttpServletResponse response) throws IOException {
        List<MovieDto> persons = getAllMovies();
        byte[] excelFile = excelService.createExcelFile(persons);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "movieList.xlsx");
        return ResponseEntity.ok().headers(headers).body(excelFile);
    }

    @PostMapping
    public ResponseEntity<MovieDto> addNewMovie(@RequestPart("file") MultipartFile file,
                                                @RequestPart("movie") String movieDtoString) throws IOException, MethodArgumentNotValidException {
        if (file.isEmpty()) {
            throw new ServiceException("file is empty cannot process request", HttpStatus.NOT_ACCEPTABLE, "NO DATA FOUND");
        }
        MovieDto movieDto = convertToDto(movieDtoString);
        validateMovieDto(movieDto);
        movieDto = movieService.saveEntityWithFile(movieDto, file);
        return ResponseEntity.ok(movieDto);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDto> updateOldMovie(
            @PathVariable Integer movieId,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestPart("movie") String movieDtoString) throws MethodArgumentNotValidException {
        if (file != null && file.isEmpty()) {
            throw new ServiceException("file is empty cannot process request", HttpStatus.NOT_ACCEPTABLE, "NO DATA FOUND");
        }
        MovieDto movieDto = convertToDto(movieDtoString);
        validateMovieDto(movieDto);
        movieDto = movieService.updateEntityWithFile(movieId, movieDto, file);
        return ResponseEntity.ok(movieDto);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteOldMovie(
            @PathVariable Integer movieId) {
        movieService.removeEntity(movieId);
        return ResponseEntity.noContent().build();
    }


    private MovieDto convertToDto(String movieDto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(movieDto, MovieDto.class);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Error processing json string: %s".formatted(e.getLocalizedMessage()), "INVALID JSON STRING");
        }
    }

    public void validateMovieDto(MovieDto movieDto) throws MethodArgumentNotValidException {
        // Manually create Errors object
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(movieDto, "movieDto");

        // Manually invoke validation
        ValidationUtils.invokeValidator(validator, movieDto, errors);

        // Check for errors and handle them as needed
        if (errors.hasErrors()) {
            log.error("not a valid movie dto");
            throw new MethodArgumentNotValidException(null, errors);
        }
    }
}
