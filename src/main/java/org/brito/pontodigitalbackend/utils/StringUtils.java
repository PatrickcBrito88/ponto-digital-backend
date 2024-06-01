package org.brito.pontodigitalbackend.utils;

public class StringUtils {

    public static String extractLastSegment(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String[] segments = input.split("/");
        return segments[segments.length - 1];
    }
}
