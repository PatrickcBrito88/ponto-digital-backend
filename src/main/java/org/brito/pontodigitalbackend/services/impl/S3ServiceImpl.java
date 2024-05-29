package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.services.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    String bucketName;

    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    @Override
    public void uploadArquivo(MultipartFile file) {
        String fileUrl = "";
        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .contentLength(file.getSize())
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
