package org.brito.pontodigitalbackend.services;

public interface S3Service {

    String uploadArquivo(String bucket, String nomeArquivo, byte[] bytesArquivo, String tipoArquivo);

    String obterUrlDownload(String bucketName, String keyName);

    String deleteArquivo(String nomeBucket, String key);
}
