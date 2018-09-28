package pl.javastart.restoffers;

public class OfferDto {

    private Long id;
    private String title;
    private String description;
    private String imgUrl;
    private double price;
    private String category;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public OfferDto offerToDto (Offer offer){
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setTitle(offer.getTitle());
        dto.setDescription(offer.getDescription());
        dto.setPrice(offer.getPrice());
        dto.setImgUrl(offer.getImgUrl());
        OfferCategory category = offer.getOfferCategory();
        dto.setCategory(category.getName());
        return dto;
    }

//    public OfferDto(){
//
//    }
//
//    //constructor:
//    public OfferDto(Offer offer) {
//        this.id = offer.getId();
//        this.title = offer.getTitle();
//        this.description = offer.getDescription();
//        this.imgUrl = offer.getImgUrl();
//        this.price = offer.getPrice();
//        OfferCategory category = offer.getOfferCategory();
//        this.category = category.getName();
//    }
}
