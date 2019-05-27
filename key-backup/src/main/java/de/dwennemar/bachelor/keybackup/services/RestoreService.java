package de.dwennemar.bachelor.keybackup.services;

import de.dwennemar.bachelor.keybackup.persist.restore.bak.KeyRepository;
import de.dwennemar.bachelor.keybackup.persist.restore.bak.ScopeRepository;
import de.dwennemar.bachelor.keybackup.persist.impl.Key;
import de.dwennemar.bachelor.keybackup.persist.impl.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestoreService {
    private final KeyRepository keyBackup;
    private final ScopeRepository scopeBackup;
    private final de.dwennemar.bachelor.keybackup.persist.restore.prod.KeyRepository keyRestore;
    private final de.dwennemar.bachelor.keybackup.persist.restore.prod.ScopeRepository scopeRestore;

    @Autowired
    public RestoreService(KeyRepository keyRepo, ScopeRepository scopeRepo,
                          de.dwennemar.bachelor.keybackup.persist.restore.prod.KeyRepository keyRestore,
                          de.dwennemar.bachelor.keybackup.persist.restore.prod.ScopeRepository scopeRestore) {
        this.keyBackup = keyRepo;
        this.scopeBackup = scopeRepo;
        this.keyRestore = keyRestore;
        this.scopeRestore = scopeRestore;
    }

    public void restoreAll() {
        Iterable<Key> keys = this.keyBackup.findAll();
        Iterable<Scope> scopes = this.scopeBackup.findAll();

        this.keyRestore.saveAll(keys);
        this.scopeRestore.saveAll(scopes);
    }
}
