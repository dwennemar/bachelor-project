package de.dwennemar.bachelor.keybackup.persist.backup;

import de.dwennemar.bachelor.keybackup.persist.impl.Key;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepo extends CrudRepository<Key, Long> {
}
