package pl.swetrak.imaginary_real_estate_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import pl.swetrak.imaginary_real_estate_agency.models.Image;
import pl.swetrak.imaginary_real_estate_agency.models.ImageCarouselDto;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.services.OfferService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class WebController {

    private final OfferService offerService;

    @Autowired
    public WebController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(path={"/", "/index", "/home"})
    public String getIndex() {
        return "index";
    }

    @GetMapping(path="/offers")
    public ModelAndView getOffers(Model model, @RequestParam("searchPhrase") Optional<String> city) {
        ModelAndView modelAndView = new ModelAndView("offers");
        List<Offer> offerList = offerService.getOffersByCity(city);
        if(city.isPresent()) modelAndView.addObject("searchPhase", city.get());
        else modelAndView.addObject("searchPhase", "");
        modelAndView.addObject("noOffers", offerList.isEmpty());
        modelAndView.addObject("offers", offerList);
        return modelAndView;
    }

    @GetMapping("/list")
    public String createOffer() {
        return "listOffer";
    }

    @GetMapping("/favourites")
    public String getFavourites() {
        return "favourite";
    }

    @GetMapping("/contact")
    public String getPublic() {return "contact";}

    @GetMapping("/details/{offerId}")
    public ModelAndView getOfferDetails(@PathVariable Long offerId) {
        ModelAndView modelAndView = new ModelAndView("details");
        Optional<Offer> offer = offerService.getOfferById(offerId);
        if(offer.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        modelAndView.addObject("offer", offer.get());
        List<Image> images = offer.get().getImages();
        List<ImageCarouselDto> newImages = IntStream.range(0, images.size()).mapToObj(
                i -> new ImageCarouselDto(images.get(i).getOffer(), images.get(i).getImageFileName(), i+1)
        ).collect(Collectors.toList());
        Map<String, Object> carouselMap = Map.of("length", images.size(), "images", newImages);
        modelAndView.addObject("carousel", carouselMap);
        return modelAndView;
    }
}
