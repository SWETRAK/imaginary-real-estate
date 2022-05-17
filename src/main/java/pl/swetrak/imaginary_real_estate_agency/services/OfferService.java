package pl.swetrak.imaginary_real_estate_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.repositories.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> getOffersByCity(Optional<String> city) {
        if (city.isEmpty()) {
            return offerRepository.findAll();
        } else {
            String citySafe = city.get();
            return offerRepository.findOffersByAddressContains(citySafe);
        }
    }

    public Offer getOfferById(Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isEmpty()) {
            return new Offer();
        } else {
            return optionalOffer.get();
        }
    }

}
