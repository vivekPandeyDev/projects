package com.vivek.pandey.api.movie.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "project")
@Getter
@Setter
public class ProjectConfig {
    private String poster;
    private FileFormat fileFormat;
}
