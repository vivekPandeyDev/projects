package com.vivek.pandey.api.movie.movie;

import com.vivek.pandey.api.movie.service.DatabaseService;
import com.vivek.pandey.api.movie.service.ExcelService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {
    @MockBean
    private  DatabaseService<MovieDto,Integer> movieService;
    @Autowired
    private  Validator validator;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  ExcelService excelService;
    private static MovieDto movieDto;


    @BeforeEach
    void setUp() throws IOException {
        when(movieService.saveEntityWithFile(any(MovieDto.class),any(MultipartFile.class))).thenReturn(movieDto);
    }

    @BeforeAll
    static void beforeAll() {
        movieDto = MovieDto.builder().title("test movie")
                .director("test director")
                .studio("test studio")
                .movieCast(Set.of("test cast 1", "test cast 2"))
                .posterName("test_poster")
                .releaseYear(2000)
                .build();
    }

    @Test
    void getAllMovies() throws Exception {
        mockMvc.perform(get("/api/movie/all")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    void paginatedMovie() {
    }

    @Test
    void getSingleMovie() {
    }

    @Test
    void downloadMovies() {
    }

    @Test
    void addNewMovie() {
    }

    @Test
    void updateOldMovie() {
    }

    @Test
    void deleteOldMovie() {
    }
}