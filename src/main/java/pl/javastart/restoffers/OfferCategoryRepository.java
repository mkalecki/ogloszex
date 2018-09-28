package pl.javastart.restoffers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferCategoryRepository extends JpaRepository<OfferCategory,Long> {
OfferCategory findByNameIs(String string);
}
