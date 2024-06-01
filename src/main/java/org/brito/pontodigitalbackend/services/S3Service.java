package org.brito.pontodigitalbackend.services;

import java.net.URL;

public interface S3Service {

    String uploadArquivo(String bucket, String nomeArquivo, byte[] bytesArquivo, String tipoArquivo);

    URL obterUrlDownload(String bucketName, String keyName);

    String deleteArquivo(String nomeBucket, String key);
}
