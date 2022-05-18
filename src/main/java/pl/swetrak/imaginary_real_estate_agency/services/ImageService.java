package pl.swetrak.imaginary_real_estate_agency.services;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.swetrak.imaginary_real_estate_agency.models.Image;
import pl.swetrak.imaginary_real_estate_agency.repositories.ImageRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ImageService {

    private ImageRepository imageRepository;
    private final String bucketName = "spring-irea";
    private String accessKey;
    private String secretKey;
    private final AWSCredentials credentials;
    private final AmazonS3 s3client;

    @Autowired
    public ImageService(ImageRepository imageRepository, Environment env) {
        this.imageRepository = imageRepository;
        this.accessKey = env.getProperty("AWS_ACCESS_KEY");
        this.secretKey = env.getProperty("AWS_PRIVATE_KEY");
        this.credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    public void upload(Long offer_id, String filename, InputStream inputStream) {
        try {
            s3client.putObject(bucketName,
                    filename,
                    inputStream,
                    new ObjectMetadata()
            );
            imageRepository.save(new Image(offer_id, filename));
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public ByteArrayOutputStream download(Long image_id) {
        Optional<Image> optionalImage = imageRepository.findImageById(image_id);
        if(optionalImage.isPresent()) {
            Image safeImage = optionalImage.get();
            try {
                S3Object object = s3client.getObject(bucketName, safeImage.getImageFileName());
                InputStream inputStream = object.getObjectContent();
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                Integer len;
                byte[] buffer = new byte[4096];
                while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                    output.write(buffer, 0, len);
                }
                return output;
            } catch (AmazonServiceException e) {
                throw new IllegalStateException("Failed to download the file", e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalStateException("Failed to download the file");
        }
    }
}