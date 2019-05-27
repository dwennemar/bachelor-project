package de.dwennemar.bachelor.keybackup.persist.restore.bak;

import de.dwennemar.bachelor.keybackup.persist.impl.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends CrudRepository<Scope, Long> {
}
