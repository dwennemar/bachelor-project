package de.dwennemar.bachelor.databackup.persist;

import de.dwennemar.bachelor.databackup.persist.impl.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {
}
