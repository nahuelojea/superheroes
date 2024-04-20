package com.w2m.superheroes.domain.repository;

import com.w2m.superheroes.domain.Superhero;

import java.util.List;
import java.util.Optional;

public interface SuperheroesRepository {

    Optional<List<Superhero>> findByName(String name);

    List<Superhero> findAll();

    Optional<Superhero> findById(Integer id);

    Superhero save(Superhero superhero);

    void deleteById(Integer id);
}
