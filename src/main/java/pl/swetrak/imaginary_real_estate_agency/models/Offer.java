package pl.swetrak.imaginary_real_estate_agency.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="my_offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;
    private String title;
    private String email;
    private String address;
    private Integer price;
    private Integer bedrooms;
    private Float bathrooms;
    private Integer area;
    private String description;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frontImage_id")
    private FrontImage frontImage;

    @OneToMany(mappedBy = "offer")
    private List<Image> images;


    public Offer() {
    }

    public Offer(String title, String email, String address, Integer price, Integer bedrooms, Float bathrooms, Integer area, String description) {
        this.title = title;
        this.email = email;
        this.address = address;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.description = description;
    }

    public Offer(String title, String email, String address, Integer price, Integer bedrooms, Float bathrooms, Integer area, String description, FrontImage frontImage, List<Image> images) {
        this.title = title;
        this.email = email;
        this.address = address;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.description = description;
        this.frontImage = frontImage;
        this.images = images;
    }

    public Offer(Long id, String title, String email, String address, Integer price, Integer bedrooms, Float bathrooms, Integer area, String description, FrontImage frontImage, List<Image> images) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.address = address;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.description = description;
        this.frontImage = frontImage;
        this.images = images;
    }

    public FrontImage getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(FrontImage frontImage) {
        this.frontImage = frontImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Float getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Float bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(id, offer.id) && Objects.equals(title, offer.title) && Objects.equals(email, offer.email) && Objects.equals(address, offer.address) && Objects.equals(price, offer.price) && Objects.equals(bedrooms, offer.bedrooms) && Objects.equals(bathrooms, offer.bathrooms) && Objects.equals(area, offer.area) && Objects.equals(description, offer.description) && Objects.equals(frontImage, offer.frontImage) && Objects.equals(images, offer.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, email, address, price, bedrooms, bathrooms, area, description, frontImage, images);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", frontImage=" + frontImage +
                ", images=" + images +
                '}';
    }
}
