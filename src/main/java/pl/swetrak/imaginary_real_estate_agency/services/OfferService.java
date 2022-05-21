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

    public Optional<Offer> getOfferById(Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        return optionalOffer;
    }

    public Offer saveOffer(Offer offer) {
        Offer newOffer = offerRepository.save(offer);
        return newOffer;
    }

}
