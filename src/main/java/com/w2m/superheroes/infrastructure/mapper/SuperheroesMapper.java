package com.w2m.superheroes.infrastructure.mapper;

import com.w2m.superheroes.domain.Superhero;
import com.w2m.superheroes.openapi.dto.SuperheroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SuperheroesMapper {

    List<SuperheroDTO> toSuperheroesDTO(List<Superhero> superheroes);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Superhero toSuperhero(SuperheroDTO superheroDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    SuperheroDTO toSuperheroDTO(Superhero superhero);
}
