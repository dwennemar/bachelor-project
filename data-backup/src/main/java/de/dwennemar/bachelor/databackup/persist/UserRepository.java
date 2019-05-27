package de.dwennemar.bachelor.databackup.persist;

import de.dwennemar.bachelor.databackup.persist.impl.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
