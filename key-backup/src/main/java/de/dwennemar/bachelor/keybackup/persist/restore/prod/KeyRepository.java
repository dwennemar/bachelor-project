package de.dwennemar.bachelor.keybackup.persist.restore.prod;

import de.dwennemar.bachelor.keybackup.persist.impl.Key;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends CrudRepository<Key, Long> {
}
