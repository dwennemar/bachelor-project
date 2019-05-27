package de.dwennemar.bachelor.dataservice.persist.basic;

import de.dwennemar.bachelor.dataservice.persist.basic.impl.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
