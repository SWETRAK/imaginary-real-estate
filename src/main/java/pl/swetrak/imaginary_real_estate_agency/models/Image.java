package pl.swetrak.imaginary_real_estate_agency.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name="my_images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Offer offer;
    private String imageFileName;

    public Image() {}

    public Image(Offer offer, String imageFileName) {
        this.offer = offer;
        this.imageFileName = imageFileName;
    }

    public Image(Long id, Offer offer, String imageFileName) {
        this.id = id;
        this.offer = offer;
        this.imageFileName = imageFileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
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
        if (!Objects.equals(offer, image.offer)) return false;
        return Objects.equals(imageFileName, image.imageFileName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (offer != null ? offer.hashCode() : 0);
        result = 31 * result + (imageFileName != null ? imageFileName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageFileName='" + imageFileName + '\'' +
                '}';
    }
}
