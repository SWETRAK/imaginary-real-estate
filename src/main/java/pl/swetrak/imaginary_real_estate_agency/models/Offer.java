package pl.swetrak.imaginary_real_estate_agency.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="offers")
public class Offer {

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
    private String title;
    private String email;
    private String address;
    private Integer price;
    private Integer bedrooms;
    private Float bathrooms;
    private Integer area;
    private String description;

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

    public Offer(Long id, String title, String email, String address, Integer price, Integer bedrooms, Float bathrooms, Integer area, String description) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.address = address;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.area = area;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offers = (Offer) o;

        if (!Objects.equals(id, offers.id)) return false;
        if (!Objects.equals(title, offers.title)) return false;
        if (!Objects.equals(email, offers.email)) return false;
        if (!Objects.equals(address, offers.address)) return false;
        if (!Objects.equals(price, offers.price)) return false;
        if (!Objects.equals(bedrooms, offers.bedrooms)) return false;
        if (!Objects.equals(bathrooms, offers.bathrooms)) return false;
        if (!Objects.equals(area, offers.area)) return false;
        return Objects.equals(description, offers.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (bedrooms != null ? bedrooms.hashCode() : 0);
        result = 31 * result + (bathrooms != null ? bathrooms.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                '}';
    }
}
