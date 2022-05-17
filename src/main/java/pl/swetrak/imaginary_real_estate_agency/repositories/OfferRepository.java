package pl.swetrak.imaginary_real_estate_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.swetrak.imaginary_real_estate_agency.models.Offer;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select o from Offer o where o.address like concat('%', ?1, '%')")
    List<Offer> findOffersByAddressContains(String address);

}
