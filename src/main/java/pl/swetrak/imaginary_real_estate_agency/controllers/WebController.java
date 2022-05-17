package pl.swetrak.imaginary_real_estate_agency.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.services.OfferService;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class WebController {

    private final OfferService offerService;

    @Autowired
    public WebController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(path={"/", "/index", "/home"})
    String getIndex() {
        return "index";
    }

    @GetMapping(path="/offers")
    ModelAndView getOffers(Model model, @RequestParam("searchPhrase") Optional<String> city) {
        ModelAndView modelAndview = new ModelAndView("offers");
        modelAndview.addObject("offers", offerService.getOffersByCity(city));
        return modelAndview;
    }

    @GetMapping("/list")
    String createOffer() {
        return "listOffer";
    }

    @GetMapping("/details/{offerId}")
    ModelAndView getOfferDetails(@PathVariable Long offerId) {
        ModelAndView modelAndView = new ModelAndView("details");
        modelAndView.addObject("offer", offerService.getOfferById(offerId));
        return modelAndView;
    }
}
