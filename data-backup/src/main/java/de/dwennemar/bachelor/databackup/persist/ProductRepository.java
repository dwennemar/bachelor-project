package de.dwennemar.bachelor.databackup.persist;

import de.dwennemar.bachelor.databackup.persist.impl.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
