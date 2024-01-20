package com.vivek.pandey.api.movie.movie;

import com.vivek.pandey.api.movie.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private FileService fileService;

    @Mock
    private HttpServletRequest mockRequest;
    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieService movieService;

    private static Movie movie;
    private static MovieDto movieDto;
    private static MockMultipartFile file;


    @BeforeEach
    void setUp() throws IOException {
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        when(fileService.uploadFile(anyString(),any(MultipartFile.class))).thenReturn("mockImg.png");
        when(mockRequest.getScheme()).thenReturn("http://localhost:8080");
        // Mock a file content as a byte array
        byte[] fileContent = "Mock file content".getBytes();
        // Create a MockMultipartFile
        file = new MockMultipartFile(
                "file",          // parameter name
                "mockImg.png",  // original file name
                "text/png",    // content type
                fileContent       // content as byte array
        );
    }

    @BeforeAll
    static void beforeAll() {
        movie = Movie.builder().title("test movie")
                .director("test director")
                .studio("test studio")
                .movieCast(Set.of("test cast 1", "test cast 2"))
                .posterName("mockImg.png")
                .releaseYear(2000)
                .build();
        movieDto = MovieDto.builder().title("test movie")
                .director("test director")
                .studio("test studio")
                .movieCast(Set.of("test cast 1", "test cast 2"))
                .posterName("test_poster")
                .releaseYear(2000)
                .build();
    }

    @Test
    void saveEntityWithFile() throws IOException {
        MovieDto savedMovieDto =  movieService.saveEntityWithFile(movieDto,file);
       Assertions.assertThat(savedMovieDto).isNotNull();
    }

    @Test
    void removeEntity() {
    }

    @Test
    void updateEntityWithFile() {
    }

    @Test
    void getEntityWithPaginatedAndSorted() {
    }

    @Test
    void getAllEntity() {
    }

    @Test
    void getEntity() {
    }
}