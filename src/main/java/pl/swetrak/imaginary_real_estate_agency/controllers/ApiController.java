package pl.swetrak.imaginary_real_estate_agency.controllers;

import org.apache.http.entity.ByteArrayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.services.ImageService;
import pl.swetrak.imaginary_real_estate_agency.services.OfferService;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping(path="/api/v1/")
public class ApiController {

    private final OfferService offerService;
    private final ImageService imageService;

    @Autowired
    public ApiController(OfferService offerService, ImageService imageService) {
        this.offerService = offerService;
        this.imageService = imageService;
    }

    @PostMapping("/create")
    Map<String, Object> createOffer(
            @RequestParam("title") Optional<String> title,
            @RequestParam("email") Optional<String> email,
            @RequestParam("address") Optional<String> address,
            @RequestParam("price") Optional<Integer> price,
            @RequestParam("apartment_area") Optional<Integer> area,
            @RequestParam("bedrooms") Optional<Integer> bedrooms,
            @RequestParam("bathrooms") Optional<Float> bathrooms,
            @RequestParam("description") Optional<String> description,
            @RequestParam("terms") Optional<Boolean> terms,
//  IMAGES
            @RequestParam("front_photo") Optional<MultipartFile> frontFile,
            @RequestParam("add_photo1") Optional<MultipartFile> addPhoto1,
            @RequestParam("add_photo2") Optional<MultipartFile> addPhoto2,
            @RequestParam("add_photo3") Optional<MultipartFile> addPhoto3,
            @RequestParam("add_photo4") Optional<MultipartFile> addPhoto4,
            @RequestParam("add_photo5") Optional<MultipartFile> addPhoto5
    ) {
        if(title.isPresent() && address.isPresent() && price.isPresent() && area.isPresent() && bedrooms.isPresent() && bathrooms.isPresent() && description.isPresent() && terms.isPresent() && frontFile.isPresent()) {

            Offer offer = new Offer(
                    title.get(),
                    email.get(),
                    address.get(),
                    price.get(),
                    bedrooms.get(),
                    bathrooms.get(),
                    area.get(),
                    description.get()
            );
            // TODO: Dodac zapisywanie oferty i image


            String name = UUID.randomUUID().toString();
            return new HashMap<String, Object>();
        } else {
            return new HashMap<String, Object>();
        }
    }

    @PostMapping("/send/email")
    Map<String, Object> sendEmail(){
        return new HashMap<String, Object>();
    }

    @PostMapping("/test")
    public void getTest(
            @RequestParam("front_photo") Optional<MultipartFile> frontFile
    ) {
        if(frontFile.isPresent()) {
            try {
                InputStream inputStream = new BufferedInputStream(frontFile.get().getInputStream());
                String nazwa = UUID.randomUUID().toString();
                imageService.upload(1L, nazwa, inputStream);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    @GetMapping("/image/get/{imageId}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable("imageId") Long id) {
        ByteArrayOutputStream downloadInputStream = imageService.download(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + "kamil pietrak.jpeg" + "\"")
                .body(downloadInputStream.toByteArray());
    }


}
