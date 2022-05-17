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
import org.springframework.stereotype.Service;
import pl.swetrak.imaginary_real_estate_agency.models.Image;
import pl.swetrak.imaginary_real_estate_agency.repositories.ImageRepository;


import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    private final String bucketName = "spring-irea";
//    TODO: Replace accessKey amd secretKey with your pair
    private final AWSCredentials credentials = new BasicAWSCredentials("accessKey", "secretKey");
    private final AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.EU_CENTRAL_1)
            .build();


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

    public byte[] download(Long image_id) {
        Optional<Image> optionalImage = imageRepository.findImageById(image_id);
        if(optionalImage.isPresent()) {
            Image safeImage = optionalImage.get();
            try {
                S3Object object = s3client.getObject(bucketName, safeImage.getImageFileName());
                S3ObjectInputStream objectContent = object.getObjectContent();
                return IOUtils.toByteArray(objectContent);
            } catch (AmazonServiceException | IOException e) {
                throw new IllegalStateException("Failed to download the file", e);
            }
        } else {
            return new byte[0];
        }
    }


}
