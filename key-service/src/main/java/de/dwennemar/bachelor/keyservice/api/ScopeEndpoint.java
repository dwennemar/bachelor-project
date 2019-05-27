package de.dwennemar.bachelor.keyservice.api;

import de.dwennemar.bachelor.keyservice.persist.ScopeRepository;
import de.dwennemar.bachelor.keyservice.persist.impl.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/scope")
public class ScopeEndpoint {

    private final ScopeRepository scopeRepository;

    @Autowired
    public ScopeEndpoint(ScopeRepository pRepository) {
        this.scopeRepository = pRepository;
    }

    @GetMapping("/all")
    public Iterable<Scope> getAllScopes() {
        return this.scopeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Scope> getScope(@PathVariable Long id) {
        return this.scopeRepository.findById(id);
    }

    @PostMapping
    public Scope newScope(@RequestBody Scope pScope) {
        return this.scopeRepository.save(pScope);
    }

    @PutMapping("/{id}")
    public Optional<Scope> updateScope(@PathVariable Long id, @RequestBody Scope pScope) {
        return this.scopeRepository.findById(id).map(scope -> {
            scope.setDefinition(pScope.getDefinition());
            scope.setNonDeletePeriod(pScope.getNonDeletePeriod());
            return this.scopeRepository.save(scope);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteScope(@PathVariable Long id) {
        this.scopeRepository.deleteById(id);
    }
}
