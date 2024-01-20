package com.vivek.pandey.api.movie.service;


import com.vivek.pandey.api.movie.movie.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The interface Database service.
 *
 * @param <P> the type parameter, reference to the entity or dto
 * @param <Q> the type parameter, reference to the primary key of entity
 */
public interface DatabaseService<P, Q> {
    Page<MovieDto> getEntityWithPaginatedAndSorted(Integer pageNumber, Integer pageSize, String sort);
    P saveEntityWithFile(P dto, MultipartFile file) throws IOException;

    void removeEntity(Q id);

    P updateEntityWithFile(Q id, P dto,MultipartFile file);

    List<P> getAllEntity();


    P getEntity(Q id);
}
