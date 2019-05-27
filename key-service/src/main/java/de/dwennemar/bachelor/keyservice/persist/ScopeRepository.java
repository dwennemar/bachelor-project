package de.dwennemar.bachelor.keyservice.persist;

import de.dwennemar.bachelor.keyservice.persist.impl.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends CrudRepository<Scope, Long> {
}
