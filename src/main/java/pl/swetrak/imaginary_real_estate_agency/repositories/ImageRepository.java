package pl.swetrak.imaginary_real_estate_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swetrak.imaginary_real_estate_agency.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
