package pl.swetrak.imaginary_real_estate_agency.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="my_front_images")
public class FrontImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;
    @OneToOne(mappedBy = "frontImage")
    @JoinColumn(name="offer_id")
    private Offer offer;
    private String imageFileName;

    public FrontImage() {
    }

    public FrontImage(Offer offer, String imageFileName) {
        this.offer = offer;
        this.imageFileName = imageFileName;
    }

    public FrontImage(Long id, Offer offer, String imageFileName) {
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

    public Offer getOffer_id() {
        return offer;
    }

    public void setOffer_id(Offer offer) {
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

        FrontImage that = (FrontImage) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(offer, that.offer)) return false;
        return Objects.equals(imageFileName, that.imageFileName);
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
        return "FrontImage{" +
                "id=" + id +
                ", imageFileName='" + imageFileName + '\'' +
                '}';
    }
}
