package com.vivek.pandey.api.movie.utility;

public class BasicUtility {

    private BasicUtility() {
    }


    public static String getFileExtension(String format) {
        return switch (format) {
            case "jpeg" -> ".jpeg";
            default -> ".png";
        };
    }

}
