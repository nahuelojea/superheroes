package com.w2m.superheroes.application;

import com.w2m.superheroes.application.mapper.SuperheroesMapper;
import com.w2m.superheroes.domain.Superhero;
import com.w2m.superheroes.domain.exception.SuperheroAlreadyExistsException;
import com.w2m.superheroes.domain.exception.SuperheroNotFoundException;
import com.w2m.superheroes.domain.repository.SuperheroesRepository;
import com.w2m.superheroes.openapi.dto.SuperheroDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuperheroesService {

    private final SuperheroesRepository superheroesRepository;
    private final SuperheroesMapper superheroesMapper;

    public SuperheroesService(SuperheroesRepository superheroesRepository,
                              SuperheroesMapper superheroesMapper) {
        this.superheroesRepository = superheroesRepository;
        this.superheroesMapper = superheroesMapper;
    }

    public List<SuperheroDTO> findByName(String name) {
        List<Superhero> superheroes = superheroesRepository.findByName(name)
                .orElseThrow(() -> new SuperheroNotFoundException("No superheroes found with name: " + name));
        return superheroes.stream()
                .map(superheroesMapper::toSuperheroDTO)
                .collect(Collectors.toList());
    }

    public List<SuperheroDTO> findAll() {
        List<Superhero> superheroes = superheroesRepository.findAll();
        return superheroes.stream()
                .map(superheroesMapper::toSuperheroDTO)
                .collect(Collectors.toList());
    }

    public SuperheroDTO findById(Integer id) {
        Superhero superhero = superheroesRepository.findById(id)
                .orElseThrow(() -> new SuperheroNotFoundException("Superhero not found with id: " + id));
        return superheroesMapper.toSuperheroDTO(superhero);
    }

    public SuperheroDTO save(SuperheroDTO superheroDTO) {
        try {
            Superhero superhero = superheroesMapper.toSuperhero(superheroDTO);
            Superhero savedSuperhero = superheroesRepository.save(superhero);
            return superheroesMapper.toSuperheroDTO(savedSuperhero);
        } catch (DataIntegrityViolationException e) {
            throw new SuperheroAlreadyExistsException("Superhero already exists with name: " + superheroDTO.getName());
        }
    }

    public SuperheroDTO update(SuperheroDTO superheroDTO) {
        try {
            Superhero superhero = superheroesMapper.toSuperhero(superheroDTO);
            if (superheroesRepository.findById(superhero.getId()).isEmpty()) {
                throw new SuperheroNotFoundException("Superhero not found with id: " + superhero.getId());
            }
            Superhero updatedSuperhero = superheroesRepository.save(superhero);
            return superheroesMapper.toSuperheroDTO(updatedSuperhero);
        } catch (DataIntegrityViolationException e) {
            throw new SuperheroAlreadyExistsException("Superhero already exists with name: " + superheroDTO.getName());
        }
    }

    public void deleteById(Integer id) {
        superheroesRepository.deleteById(id);
    }
}
