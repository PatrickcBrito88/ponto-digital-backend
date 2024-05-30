package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.services.S3Service;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    @Override
    public void uploadArquivo(String bucket, String caminhoArquivo, byte[] bytesArquivo, String tipoArquivo) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(caminhoArquivo)
                    .contentType(tipoArquivo)
                    .contentLength((long) bytesArquivo.length)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytesArquivo));
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}
