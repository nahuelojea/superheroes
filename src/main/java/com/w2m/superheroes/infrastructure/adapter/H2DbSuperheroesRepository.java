package com.w2m.superheroes.infrastructure.adapter;

import com.w2m.superheroes.domain.Superhero;
import com.w2m.superheroes.domain.repository.SuperheroesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Primary
public class H2DbSuperheroesRepository implements SuperheroesRepository {

    private static final String SUPERHEROES_CACHE = "superheroes";

    private final SuperheroesJpaRepository superheroesJpaRepository;

    @Override
    public Optional<List<Superhero>> findByName(String name) {
        return superheroesJpaRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Cacheable(SUPERHEROES_CACHE)
    public List<Superhero> findAll() {
        return superheroesJpaRepository.findAll();
    }

    @Override
    public Optional<Superhero> findById(Integer id) {
        return superheroesJpaRepository.findById(id);
    }

    @Override
    @CacheEvict(value = SUPERHEROES_CACHE, allEntries = true)
    public Superhero save(Superhero superhero) {
        return superheroesJpaRepository.save(superhero);
    }

    @Override
    public void deleteById(Integer id) {
        superheroesJpaRepository.deleteById(id);
    }
}
