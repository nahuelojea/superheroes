package com.w2m.superheroes.infrastructure.mapper;

import com.w2m.superheroes.domain.Superhero;
import com.w2m.superheroes.openapi.dto.SuperheroDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperheroesMapperTest {

    private final SuperheroesMapper mapper = new SuperheroesMapperImpl();

    @Test
    void shouldReturnSuperheroWhenToSuperhero() {
        SuperheroDTO superheroDTO = new SuperheroDTO();
        superheroDTO.setId(1);
        superheroDTO.setName("Superman");

        Superhero superhero = mapper.toSuperhero(superheroDTO);

        assertEquals(superheroDTO.getId(), superhero.getId());
        assertEquals(superheroDTO.getName(), superhero.getName());
    }

    @Test
    void shouldReturnSuperheroDTOWhenToSuperheroDTO() {
        // Arrange
        Superhero superhero = new Superhero(1, "Superman");

        SuperheroDTO superheroDTO = mapper.toSuperheroDTO(superhero);

        assertEquals(superhero.getId(), superheroDTO.getId());
        assertEquals(superhero.getName(), superheroDTO.getName());
    }

    @Test
    void shouldReturnSuperheroesDTOWhenToSuperheroesDTO() {
        List<Superhero> superheroes = Arrays.asList(
                new Superhero(1, "Superman"),
                new Superhero(2, "Spiderman")
        );

        List<SuperheroDTO> superheroDTOs = mapper.toSuperheroesDTO(superheroes);

        assertEquals(superheroes.size(), superheroDTOs.size());
        superheroDTOs.forEach(superheroDTO -> {
            Superhero superhero = superheroes.stream()
                    .filter(s -> s.getId().equals(superheroDTO.getId()))
                    .findFirst()
                    .orElse(null);
            assertNotNull(superhero);
            assertEquals(superhero.getId(), superheroDTO.getId());
            assertEquals(superhero.getName(), superheroDTO.getName());
        });
    }
}
