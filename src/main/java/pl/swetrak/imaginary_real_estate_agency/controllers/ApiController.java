package pl.swetrak.imaginary_real_estate_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.swetrak.imaginary_real_estate_agency.models.FrontImage;
import pl.swetrak.imaginary_real_estate_agency.models.Image;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.services.EmailSenderService;
import pl.swetrak.imaginary_real_estate_agency.services.FrontImageService;
import pl.swetrak.imaginary_real_estate_agency.services.ImageService;
import pl.swetrak.imaginary_real_estate_agency.services.OfferService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(path="/api/v1/")
public class ApiController {

    private final OfferService offerService;
    private final ImageService imageService;
    private final FrontImageService frontImageService;
    private final EmailSenderService emailSenderService;

    @Autowired
    public ApiController(OfferService offerService, ImageService imageService, FrontImageService frontImageService, EmailSenderService emailSenderService) {
        this.offerService = offerService;
        this.imageService = imageService;
        this.frontImageService = frontImageService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/create")
    Map<String, Object> createOffer(
            @RequestParam("title") Optional<String> title,
            @RequestParam("email") Optional<String> email,
            @RequestParam("address") Optional<String> address,
            @RequestParam("price") Optional<Integer> price,
            @RequestParam("area") Optional<Integer> area,
            @RequestParam("bedrooms") Optional<Integer> bedrooms,
            @RequestParam("bathrooms") Optional<Float> bathrooms,
            @RequestParam("description") Optional<String> description,
            @RequestParam("terms") Optional<Boolean> terms,
//  IMAGES
            @RequestParam("front_photo") Optional<MultipartFile> frontPhoto,
            @RequestParam("add_photo1") Optional<MultipartFile> addPhoto1,
            @RequestParam("add_photo2") Optional<MultipartFile> addPhoto2,
            @RequestParam("add_photo3") Optional<MultipartFile> addPhoto3,
            @RequestParam("add_photo4") Optional<MultipartFile> addPhoto4,
            @RequestParam("add_photo5") Optional<MultipartFile> addPhoto5
    ) throws IOException {
        if (title.isPresent() && email.isPresent() && address.isPresent() && price.isPresent() && area.isPresent() && bedrooms.isPresent() && bathrooms.isPresent() && description.isPresent() && terms.isPresent() && frontPhoto.isPresent()) {
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
            offer = offerService.saveOffer(offer);
            String frontPhotoName = UUID.randomUUID() + ".jpeg";
            FrontImage fimg = frontImageService.upload(offer, frontPhotoName, new BufferedInputStream(frontPhoto.get().getInputStream()));
            offer.setFrontImage(fimg);
            List<Optional<MultipartFile>> additionalPhotos = List.of(addPhoto1, addPhoto2, addPhoto3, addPhoto4, addPhoto5);
            List<Image> list_of_images = new ArrayList<>();
            for (Optional<MultipartFile> photo : additionalPhotos) {
                if (photo.isPresent()) {
                    String name = UUID.randomUUID() + ".jpeg";
                    Image image = imageService.upload(offer, name, new BufferedInputStream(photo.get().getInputStream()));
                    list_of_images.add(image);
                }
            }
            offer.setImages(list_of_images);
            offerService.saveOffer(offer);
            return Map.of("status", "success");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }


    @PostMapping("/send/email/{offerId}")
    Map<String, Object> sendEmail(
            @PathVariable("offerId") Optional<Long> offerId,
            @RequestParam("email") Optional<String> userEmail
    ){
        if(offerId.isPresent() && userEmail.isPresent()) {

            Optional<Offer> offer = offerService.getOfferById(offerId.get());

            if(offer.isEmpty()) return Map.of("status", "failure");

            emailSenderService.sendEmail(offer.get().getEmail(), "Someone is interested into your offer", userEmail.get());
            emailSenderService.sendEmail(userEmail.get(), "You're interest into some offers", offer.get().getEmail());

            return Map.of("status", "success");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }

    }
}
