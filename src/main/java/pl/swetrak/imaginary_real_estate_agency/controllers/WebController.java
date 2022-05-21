package pl.swetrak.imaginary_real_estate_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.services.ImageService;
import pl.swetrak.imaginary_real_estate_agency.services.OfferService;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {

    private final OfferService offerService;
    private final ImageService imageService;

    @Autowired
    public WebController(OfferService offerService, ImageService imageService) {
        this.offerService = offerService;
        this.imageService = imageService;
    }

    @GetMapping(path={"/", "/index", "/home"})
    public String getIndex() {
        return "index";
    }

    @GetMapping(path="/offers")
    public ModelAndView getOffers(Model model, @RequestParam("searchPhrase") Optional<String> city) {
        ModelAndView modelAndview = new ModelAndView("offers");
        modelAndview.addObject("offers", offerService.getOffersByCity(city));
        return modelAndview;
    }

    @GetMapping("/list")
    public String createOffer() {
        return "listOffer";
    }

    @GetMapping("/details/{offerId}")
    public ModelAndView getOfferDetails(@PathVariable Long offerId) {
        ModelAndView modelAndView = new ModelAndView("details");
        Offer oferta = offerService.getOfferById(offerId);
        modelAndView.addObject("offer", oferta);
        System.out.println(oferta);
        return modelAndView;
    }




}
