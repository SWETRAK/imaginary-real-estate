package pl.swetrak.imaginary_real_estate_agency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;
import pl.swetrak.imaginary_real_estate_agency.repositories.OfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        }
        String citySafe = city.get();
        return offerRepository.findOffersByAddressContains(citySafe);
    }

    public List<Offer> getOfferByIdInRange(Optional<List<Long>> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<Offer>();
        }
        List<Long> safeId = ids.get();
        System.out.println(safeId);
        List<Offer> offers = offerRepository.findOffersByIdIn(safeId);
        return offerRepository.findOffersByIdIn(safeId);
    }

    public Optional<Offer> getOfferById(Long id) {
        return offerRepository.findById(id);
    }

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

}
