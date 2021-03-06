package pl.swetrak.imaginary_real_estate_agency.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.swetrak.imaginary_real_estate_agency.models.FrontImage;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.repositories.FrontImageRepository;


import java.io.InputStream;
import java.util.Objects;


@Service
public class FrontImageService {

    private final FrontImageRepository frontImageRepository;
    private final String bucketName = "spring-irea";
    private final AmazonS3 s3client;

    @Autowired
    public FrontImageService(FrontImageRepository frontImageRepository, Environment env) {
        try {
            this.frontImageRepository = frontImageRepository;
            String accessKey = env.getProperty("AWS_ACCESS_KEY");
            String secretKey = env.getProperty("AWS_PRIVATE_KEY");
            AWSCredentials credentials = new BasicAWSCredentials(Objects.requireNonNull(accessKey), Objects.requireNonNull(secretKey));
            this.s3client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.EU_CENTRAL_1)
                    .build();
        } catch (NullPointerException e) {
            throw new IllegalStateException("AWS S3 error", e);
        }
    }

    public FrontImage upload(Offer offer, String filename, InputStream inputStream) {
        try {
            s3client.putObject(bucketName,
                    filename,
                    inputStream,
                    new ObjectMetadata()
            );
            return frontImageRepository.save(new FrontImage(offer, filename));
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }
}
