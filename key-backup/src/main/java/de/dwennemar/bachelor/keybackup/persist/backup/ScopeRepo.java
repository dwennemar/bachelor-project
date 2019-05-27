package de.dwennemar.bachelor.keybackup.persist.backup;

import de.dwennemar.bachelor.keybackup.persist.impl.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepo extends CrudRepository<Scope, Long> {
}
