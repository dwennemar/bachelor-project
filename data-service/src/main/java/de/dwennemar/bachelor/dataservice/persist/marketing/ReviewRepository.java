package de.dwennemar.bachelor.dataservice.persist.marketing;

import de.dwennemar.bachelor.dataservice.persist.marketing.impl.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> getByProductId(Long id);

    List<Review> getByAuthorId(Long id);
}
