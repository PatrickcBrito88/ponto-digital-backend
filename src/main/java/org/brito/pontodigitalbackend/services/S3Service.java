package org.brito.pontodigitalbackend.services;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    void uploadArquivo(MultipartFile file);
}
