package pl.swetrak.imaginary_real_estate_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swetrak.imaginary_real_estate_agency.models.Image;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findImageById(Long image_id);
}
