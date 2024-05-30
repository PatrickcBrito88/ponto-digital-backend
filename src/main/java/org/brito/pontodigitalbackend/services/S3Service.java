package org.brito.pontodigitalbackend.services;

public interface S3Service {

    void uploadArquivo(String bucket, String nomeArquivo, byte[] bytesArquivo, String tipoArquivo);
}
