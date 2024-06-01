package org.brito.pontodigitalbackend.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Configuration
public class AmazonS3Config {

    @Value("${aws.s3.endpoint}")
    private String s3Endpoint;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    @Profile("local")
    public S3Client localS3Client() {
        return S3Client.builder()
                .endpointOverride(URI.create(s3Endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("access", "key")))
                .serviceConfiguration(S3Configuration.builder().pathStyleAccessEnabled(true).build())
                .build();
    }

    @Bean
    @Profile("AWS")
    public S3Client awsS3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .build();
    }

    @Bean
    @Profile("local")
    public S3Presigner localS3Presigner() {
        return S3Presigner.builder()
                .endpointOverride(URI.create(s3Endpoint))
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("access", "key")))
                .build();
    }

    @Bean
    @Profile("AWS")
    public S3Presigner awsS3Presigner() {
        return S3Presigner.builder()
                .region(Region.of(region))
                .build();
    }


}
