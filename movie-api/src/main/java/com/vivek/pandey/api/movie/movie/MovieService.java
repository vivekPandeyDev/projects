package com.vivek.pandey.api.movie.movie;

import com.vivek.pandey.api.movie.exception.ServiceException;
import com.vivek.pandey.api.movie.service.DatabaseService;
import com.vivek.pandey.api.movie.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MovieService implements DatabaseService<MovieDto, Integer> {

    private final MovieRepository movieRepository;
    private final ModelMapper mapper;
    private final FileService fileService;
    private final HttpServletRequest request;

    @Value("${project.fileFormat}")
    private String fileFormat;

    @Value("${project.poster}")
    private String filePath;

    private static final String MOVIE_NOT_FOUND = "MOVIE NOT FOUND";

    @Override
    public MovieDto saveEntityWithFile(@NotNull MovieDto dto, @NotNull MultipartFile file) throws IOException {
        String uploadedFileNameWithExtension = fileService.uploadFile(dto.getPosterName(), file);
        log.info("upload file with name with extension: {}", uploadedFileNameWithExtension);
        Movie movie = mapToEntity(dto);
        // change posterName to {posterName}.extension
        movie.setPosterName(uploadedFileNameWithExtension);
        // every time new movie added even dto contains id prevent accidental updates
        movie.setMovieId(null);
        movie = movieRepository.save(movie);
        dto = mapToDto(movie);
        String posterUrl = MessageFormat.format("{0}/api/file/{1}", getBaseUrl(), dto.getPosterName());
        dto.setPosterUrl(posterUrl);
        log.info("poster url: {}", dto.getPosterUrl());
        return dto;
    }

    @Override
    @CacheEvict(value = "movie", key = "#id")
    public void removeEntity(@NotNull Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ServiceException("No movie found with given id: %d".formatted(id), HttpStatus.NOT_FOUND, MOVIE_NOT_FOUND));
        Path moviePath = Paths.get(filePath, movie.getPosterName());
        try {
            Files.deleteIfExists(moviePath);
            movieRepository.delete(movie);
        } catch (Exception e) {
            log.error("cannot delete file with path : {}", moviePath);
            log.error("error message: {}", e.getMessage());
            throw new ServiceException("Cannot remove movie with id: %d, reason: %s".formatted(id, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR");
        }

    }

    @Override
    @CachePut(value = "movie", key = "#id")
    public MovieDto updateEntityWithFile(Integer id, MovieDto dto, MultipartFile file) {
        Movie savedMovie = movieRepository.findById(id).orElseThrow(() -> new ServiceException("No movie found with given id: %d".formatted(id), HttpStatus.NOT_FOUND, MOVIE_NOT_FOUND));
        String savedFileName = savedMovie.getPosterName();
        if (file != null) {
            Path moviePath = Paths.get(filePath, savedFileName);
            try {
                Path tempFilePath = Files.createTempFile("temp", null);
                Files.move(moviePath, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
                //getting new savedFileName and updating the dto
                String newSavedFileName = fileService.uploadFile(dto.getPosterName(), file);
                Files.deleteIfExists(tempFilePath);
                dto.setPosterName(newSavedFileName);
            } catch (IOException e) {
                throw new ServiceException("cannot delete file with name: %s, %s".formatted(savedFileName, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR, "SERVER ERROR");
            }
        } else {
            dto.setPosterName(savedFileName);
        }
        Movie movie = mapToEntity(dto);
        movie.setMovieId(id);
        movie = movieRepository.save(movie);
        dto = mapToDto(movie);
        String posterUrl = getBaseUrl() + "/api/file/" + dto.getPosterName();
        dto.setPosterUrl(posterUrl);
        log.info("poster url: {}", dto.getPosterUrl());
        return dto;
    }

    @Override
    public Page<MovieDto> getEntityWithPaginatedAndSorted(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (Objects.nonNull(sort)) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return movieRepository.findAll(pageable).map(this::mapToDto);

    }

    public List<MovieDto> getAllEntity() {
        return movieRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Override
    @Cacheable(value = "movie", key = "#id")
    public MovieDto getEntity(Integer id) {
        return mapper.map(movieRepository.findById(id).orElseThrow(
                () -> new ServiceException("No movie found with given id: %s".formatted(id), HttpStatus.NOT_FOUND, MOVIE_NOT_FOUND)
        ), MovieDto.class);
    }

    private MovieDto mapToDto(@NotNull Movie movie) {
        MovieDto movieDto = mapper.map(movie, MovieDto.class);
        String posterUrl = getBaseUrl() + "/api/file/" + movie.getPosterName();
        movieDto.setPosterUrl(posterUrl);
        return movieDto;
    }

    private Movie mapToEntity(@NotNull MovieDto dto) {
        return mapper.map(dto, Movie.class);
    }

    private String getBaseUrl() {
        return ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
    }
}
