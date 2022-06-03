package pl.swetrak.imaginary_real_estate_agency.models;

public class ImageCarouselDto {
    private Offer offer;
    private String imageFileName;
    private Integer index;

    public ImageCarouselDto(Offer offer, String imageFileName, Integer index) {
        this.offer = offer;
        this.imageFileName = imageFileName;
        this.index = index;
    }

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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageCarouselDto that = (ImageCarouselDto) o;

        if (offer != null ? !offer.equals(that.offer) : that.offer != null) return false;
        if (imageFileName != null ? !imageFileName.equals(that.imageFileName) : that.imageFileName != null)
            return false;
        return index != null ? index.equals(that.index) : that.index == null;
    }

    @Override
    public int hashCode() {
        int result = offer != null ? offer.hashCode() : 0;
        result = 31 * result + (imageFileName != null ? imageFileName.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ImageCarouselDto{" +
                "offer=" + offer +
                ", imageFileName='" + imageFileName + '\'' +
                ", index=" + index +
                '}';
    }
}
