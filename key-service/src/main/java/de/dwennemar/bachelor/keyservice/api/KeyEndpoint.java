package de.dwennemar.bachelor.keyservice.api;

import de.dwennemar.bachelor.keyservice.persist.KeyRepository;
import de.dwennemar.bachelor.keyservice.persist.impl.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/key")
public class KeyEndpoint {

    private final KeyRepository keyRepository;

    @Autowired
    public KeyEndpoint(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @GetMapping("/user/{userId}/scope/{scopeId}")
    public Iterable<Key> getKeyByUserIdAndScope(@PathVariable Long userId, @PathVariable Long scopeId) {
        return this.keyRepository.findByUserIdAndScopeId(userId, scopeId);
    }

    @GetMapping("/all")
    public Iterable<Key> getAllKeys() {
        return this.keyRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Iterable<Key> getKeyByUserId(@PathVariable Long userId) {
        return keyRepository.findByUserId(userId);
    }

    @PostMapping
    public Key newKey(@RequestBody Key pKey){
        return keyRepository.save(pKey);
    }

    @DeleteMapping("/user/{userId}/scope/{scopeId}")
    public void deleteKey(@PathVariable Long userId, @PathVariable Long scopeId) {
        this.keyRepository.deleteByUserIdAndScopeId(userId, scopeId);
    }
}
