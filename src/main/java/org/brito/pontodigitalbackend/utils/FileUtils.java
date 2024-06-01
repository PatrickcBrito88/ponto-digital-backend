package org.brito.pontodigitalbackend.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class FileUtils {

    public static String getFileName(MultipartFile file) {
        return Optional.ofNullable(file.getOriginalFilename()).orElse("");
    }

    public static String getFileExtension(MultipartFile file) {
        String fileName = getFileName(file);
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex != -1 && dotIndex < fileName.length() - 1) ? fileName.substring(dotIndex + 1) : "";
    }

}
