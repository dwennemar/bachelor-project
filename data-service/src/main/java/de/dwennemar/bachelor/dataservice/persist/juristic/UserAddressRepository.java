package de.dwennemar.bachelor.dataservice.persist.juristic;

import de.dwennemar.bachelor.dataservice.persist.juristic.impl.UserAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {
}
