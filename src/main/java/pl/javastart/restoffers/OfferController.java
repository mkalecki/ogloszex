package pl.javastart.restoffers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OfferController {

    private OfferRepository offerRepository;
    private OfferCategoryRepository offerCategoryRepository;

    public OfferRepository getOfferRepository() {
        return offerRepository;
    }

    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public OfferCategoryRepository getOfferCategoryRepository() {
        return offerCategoryRepository;
    }

    public void setOfferCategoryRepository(OfferCategoryRepository offerCategoryRepository) {
        this.offerCategoryRepository = offerCategoryRepository;
    }

    public OfferController(OfferRepository offerRepository, OfferCategoryRepository offerCategoryRepository) {
        this.offerRepository = offerRepository;
        this.offerCategoryRepository = offerCategoryRepository;
    }


    @GetMapping("/api/offers")
    @ResponseBody
    public List<OfferDto> getOfferList(@RequestParam(defaultValue = "", required = false) String title) {

        List<Offer> offers;
        OfferDto dto = new OfferDto();
        List<OfferDto> offerDtos = new ArrayList<>();

        if (title != null) {
            offers = offerRepository.findByTitleContains(title);

        } else {
            offers = offerRepository.findAll();

        }
//konwersja do wymaganego formatu (category):
        for (Offer offer : offers) {
            offerDtos.add(dto.offerToDto(offer));
        }
        return offerDtos;
    }

    @GetMapping("/api/offers/count")
    @ResponseBody
    public long offersCount() {
        return offerRepository.count();
    }


    @GetMapping("/api/categories/names")
    @ResponseBody
    public List<String> getOfferCategoryNames() {

        List<String> categoryNames = new ArrayList<>();
        List<OfferCategory> offerCategories = offerCategoryRepository.findAll();

        for (OfferCategory temp : offerCategories) {
            categoryNames.add(temp.getName());
        }
        return categoryNames;
    }

    @GetMapping("api/offers/{id}")
    @ResponseBody
    public ResponseEntity<Offer> getOneOffer(@PathVariable Long id) {
        Optional<Offer> optional = offerRepository.findById(id);

        if (optional.isPresent()) {
            Offer offer = optional.get();

            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/api/offers")
    @ResponseBody
    public OfferDto addOffer(@RequestBody OfferDto offerDto) {
        Offer offer = new Offer();
        offer.setTitle(offerDto.getTitle());
        offer.setDescription(offerDto.getDescription());
        offer.setImgUrl(offerDto.getImgUrl());
        offer.setPrice(offerDto.getPrice());
        offer.setOfferCategory(offerCategoryRepository.findByNameIs(offerDto.getCategory()));

        offerRepository.save(offer);

        Long lastId = offersCount();
        Offer addedOffer = offerRepository.getOne(lastId);
        OfferDto addedOfferDto = new OfferDto();
        return addedOfferDto.offerToDto(addedOffer);
    }

    @GetMapping("/api/categories")
    @ResponseBody
    public List<OfferCategoryDto> getOfferCategories() {
        List<OfferCategoryDto> offerCategoryDtos = new ArrayList<OfferCategoryDto>();
        List<OfferCategory> offerCategories = offerCategoryRepository.findAll();
        OfferCategoryDto offerCategoryDto = new OfferCategoryDto();

        for (OfferCategory temp : offerCategories) {
            offerCategoryDto = offerCategoryDto.categoryToDto(temp);

            offerCategoryDtos.add(offerCategoryDto);
        }
        return offerCategoryDtos;
    }

    @PostMapping("api/categories")
    @ResponseBody
    public OfferCategoryDto addCategory(@RequestBody OfferCategoryDto newCategoryDto) {

        OfferCategory newCategory = new OfferCategory();
        newCategory.setName(newCategoryDto.getName());
        newCategory.setDescription(newCategoryDto.getDescription());
        newCategory.setOffers(new ArrayList<Offer>());


        offerCategoryRepository.save(newCategory);

        long lastCategoryId = offerCategoryRepository.count();
        newCategory = offerCategoryRepository.getOne(lastCategoryId);

        OfferCategoryDto added = new OfferCategoryDto();
        added = added.categoryToDto(newCategory);
        return added;
    }

    @DeleteMapping("/api/offers/{id}")
    @ResponseBody
    public void deleteOffer(@PathVariable long id) {
        offerRepository.deleteById(id);
    }
}
