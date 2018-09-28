package pl.javastart.restoffers;

public class OfferCategoryDto {

    private String name;
    private String description;
    private long offers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOffers() {
        return offers;
    }

    public void setOffers(long offers) {
        this.offers = offers;
    }

    public OfferCategoryDto categoryToDto(OfferCategory offerCategory) {
        OfferCategoryDto binded = new OfferCategoryDto();
        binded.setName(offerCategory.getName());
        binded.setDescription(offerCategory.getDescription());
        long numOfCategories = offerCategory.getOffers().size();
        binded.setOffers(numOfCategories);
        return binded;
    }
}
