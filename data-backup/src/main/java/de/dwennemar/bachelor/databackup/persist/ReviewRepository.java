package de.dwennemar.bachelor.databackup.persist;

import de.dwennemar.bachelor.databackup.persist.impl.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
