package com.w2m.superheroes.infrastructure.adapter;

import com.w2m.superheroes.domain.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuperheroesJpaRepository extends JpaRepository<Superhero, Integer> {

    Optional<List<Superhero>> findByNameContainingIgnoreCase(String name);
}
