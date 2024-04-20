package com.w2m.superheroes.application;

import com.w2m.superheroes.domain.Superhero;
import com.w2m.superheroes.domain.exception.SuperheroAlreadyExistsException;
import com.w2m.superheroes.domain.exception.SuperheroNotFoundException;
import com.w2m.superheroes.domain.repository.SuperheroesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheroesService {

    private final SuperheroesRepository superheroesRepository;

    public SuperheroesService(SuperheroesRepository superheroesRepository) {
        this.superheroesRepository = superheroesRepository;
    }

    public List<Superhero> findByName(String name) {
        return superheroesRepository.findByName(name).orElseThrow(() -> new SuperheroNotFoundException("No superheroes found with name: " + name));
    }

    public List<Superhero> findAll() {
        return superheroesRepository.findAll();
    }

    public Superhero findById(Integer id) {
        return superheroesRepository.findById(id)
                .orElseThrow(() -> new SuperheroNotFoundException("Superhero not found with id: " + id));
    }

    public Superhero save(Superhero superhero) {
        if (superheroesRepository.findById(superhero.getId()).isPresent()) {
            throw new SuperheroAlreadyExistsException("Superhero already exists with id: " + superhero.getId());
        }
        return superheroesRepository.save(superhero);
    }

    public Superhero update(Superhero superhero) {
        if (superheroesRepository.findById(superhero.getId()).isEmpty()) {
            throw new SuperheroNotFoundException("Superhero not found with id: " + superhero.getId());
        }
        return superheroesRepository.save(superhero);
    }

    public void deleteById(Integer id) {
        superheroesRepository.deleteById(id);
    }
}
