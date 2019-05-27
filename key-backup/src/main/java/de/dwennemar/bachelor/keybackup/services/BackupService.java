package de.dwennemar.bachelor.keybackup.services;

import de.dwennemar.bachelor.keybackup.persist.backup.KeyRepo;
import de.dwennemar.bachelor.keybackup.persist.backup.ScopeRepo;
import de.dwennemar.bachelor.keybackup.persist.impl.Key;
import de.dwennemar.bachelor.keybackup.persist.impl.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BackupService {

    private final ApiService apiService;
    private final KeyRepo keyRepo;
    private final ScopeRepo scopeRepo;

    @Autowired
    public BackupService(ApiService apiService, KeyRepo keyRepo, ScopeRepo scopeRepo) {
        this.apiService = apiService;
        this.keyRepo = keyRepo;
        this.scopeRepo = scopeRepo;
    }

    public void backupAll() {
        List<Key> keys = this.apiService.getKey();
        List<Scope> scopes = this.apiService.getScope();

        checkKeyDeadline(keys);

        this.keyRepo.saveAll(keys);
        this.scopeRepo.saveAll(scopes);
    }

    private void checkKeyDeadline(List<Key> keys) {
        keys.forEach(key -> {
            if (key.getDeadline().isBefore(LocalDate.now())) {
                this.apiService.deleteKey(key.getUserId(), key.getScope().getId());

                keys.remove(key);
            }
        });
    }

}
