package pl.swetrak.imaginary_real_estate_agency.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.swetrak.imaginary_real_estate_agency.models.FrontImage;

@Repository
public interface FrontImageRepository extends JpaRepository<FrontImage, Long> {

}
