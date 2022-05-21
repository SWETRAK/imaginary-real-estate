package pl.swetrak.imaginary_real_estate_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.swetrak.imaginary_real_estate_agency.models.FrontImage;
import pl.swetrak.imaginary_real_estate_agency.models.Image;

import java.util.Optional;

public interface FrontImageRepository extends JpaRepository<FrontImage, Long> {

    Optional<FrontImage> findFrontImageById(Long image_id);
}
