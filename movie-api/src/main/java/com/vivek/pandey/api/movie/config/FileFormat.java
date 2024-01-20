package com.vivek.pandey.api.movie.config;

public enum FileFormat {
    PNG("image/png"),
    JPEG("image/jpeg");
    private final String mimeType;

    FileFormat(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }
}
