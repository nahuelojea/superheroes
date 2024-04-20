package com.w2m.superheroes.infrastructure.config;

import com.w2m.superheroes.infrastructure.adapter.SuperheroesJpaRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = SuperheroesJpaRepository.class)
public class H2DBConfiguration {
}
