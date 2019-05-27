package de.dwennemar.bachelor.keybackup.registry;

import de.dwennemar.bachelor.keybackup.registry.impl.Registry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistryRepository extends CrudRepository<Registry, Long> {

    List<Registry> findByCreated(LocalDate date);
}
