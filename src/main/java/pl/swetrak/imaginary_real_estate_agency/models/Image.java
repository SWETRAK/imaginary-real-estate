package pl.swetrak.imaginary_real_estate_agency.models;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="images")
public class Image {


    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private Long offer_id;
    private String imageFileName;

    public Image() {}

    public Image(Long offer_id, String imageFileName) {
        this.offer_id = offer_id;
        this.imageFileName = imageFileName;
    }

    public Image(Long id, Long offer_id, String imageFileName) {
        this.id = id;
        this.offer_id = offer_id;
        this.imageFileName = imageFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(Long offer_id) {
        this.offer_id = offer_id;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (!Objects.equals(id, image.id)) return false;
        if (!Objects.equals(offer_id, image.offer_id)) return false;
        return Objects.equals(imageFileName, image.imageFileName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (offer_id != null ? offer_id.hashCode() : 0);
        result = 31 * result + (imageFileName != null ? imageFileName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", offer_id=" + offer_id +
                ", imageFileName='" + imageFileName + '\'' +
                '}';
    }
}
