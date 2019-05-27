package de.dwennemar.bachelor.keyservice.persist;

import de.dwennemar.bachelor.keyservice.persist.impl.Key;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyRepository extends CrudRepository<Key, Long> {

    /**
     * Fetches all Keys for a specific user.
     *
     * @param userId UserId from Users that's keys should be fetched
     * @return {@link List} of all Keys that are stored for this User
     */
    List<Key> findByUserId(Long userId);

    List<Key> findByUserIdAndScopeId(Long userId, Long scopeId);

    void deleteByUserIdAndScopeId(Long userId, Long scopeId);
}
