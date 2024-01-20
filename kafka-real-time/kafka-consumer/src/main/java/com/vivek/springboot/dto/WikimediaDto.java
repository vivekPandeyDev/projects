package com.vivek.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WikimediaDto {
        private String id;
        private String type;
        private String namespace;
        private String title;
        private String comment;
        private long timestamp;
        private String user;
        private boolean bot;
        @JsonProperty("server_name")
        private String serverName;
        private String wiki;
        private String parsedcomment;
}