package org.brito.pontodigitalbackend.services.impl;

import org.brito.pontodigitalbackend.exception.NaoEncontradoException;
import org.brito.pontodigitalbackend.exception.NegocioException;
import org.brito.pontodigitalbackend.services.S3Service;
import org.brito.pontodigitalbackend.utils.MessageUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.URL;
import java.time.Duration;

import static org.brito.pontodigitalbackend.utils.StringUtils.extractLastSegment;

@Service
public class S3ServiceImpl implements S3Service {

    public static final int DURACAO_LINK_DOWNLOAD = 10;

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;


    public S3ServiceImpl(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    public String uploadArquivo(String bucket, String caminhoArquivo, byte[] bytesArquivo, String tipoArquivo) {
        String caminhoArquivoUnico = getUniqueFileName(bucket, caminhoArquivo);
        try {

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(caminhoArquivoUnico)
                    .contentType(tipoArquivo)
                    .contentLength((long) bytesArquivo.length)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytesArquivo));

        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return extractLastSegment(caminhoArquivoUnico);
    }

    @Override
    public URL obterUrlDownload(String nomeBucket, String key) {
        if (!fileExists(nomeBucket, key)){
            throw new NaoEncontradoException(
                    MessageUtils.buscaMensagemValidacao("arquivo.nao.existe.bucket", key));
        }
       try{
           Duration duration = Duration.ofMinutes(DURACAO_LINK_DOWNLOAD);
           PresignedGetObjectRequest presignedGetObjectRequest =
                   s3Presigner.presignGetObject(
                           builder -> builder.getObjectRequest(
                                   req -> req
                                           .bucket(nomeBucket)
                                           .key(key))
                                   .signatureDuration(duration));

           return presignedGetObjectRequest.url();
        } catch (Exception e){
            throw new NegocioException(e.getMessage());
        }
    }

    public String deleteArquivo(String nomeBucket, String key) {
        if (!fileExists(nomeBucket, key)){
            throw new NaoEncontradoException(
                    MessageUtils.buscaMensagemValidacao("arquivo.nao.existe.bucket", key));
        }
        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(nomeBucket)
                    .key(key)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);

        } catch (SdkClientException e) {
            e.printStackTrace();
        }
        return extractLastSegment(key);
    }


    private String getUniqueFileName(String bucket, String caminhoArquivo) {
        String uniqueFileName = caminhoArquivo;
        int counter = 1;

        while (fileExists(bucket, uniqueFileName)) {
            int dotIndex = caminhoArquivo.lastIndexOf('.');
            if (dotIndex != -1) {
                uniqueFileName = caminhoArquivo.substring(0, dotIndex) + "_" + counter + caminhoArquivo.substring(dotIndex);
            } else {
                uniqueFileName = caminhoArquivo + "_" + counter;
            }
            counter++;
        }

        return uniqueFileName;
    }

    private boolean fileExists(String bucket, String key) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            HeadObjectResponse headObjectResponse = s3Client.headObject(headObjectRequest);
            return headObjectResponse != null;
        } catch (NoSuchKeyException e) {
            return false;
        } catch (SdkClientException e) {
            e.printStackTrace();
            return false;
        }
    }
}
