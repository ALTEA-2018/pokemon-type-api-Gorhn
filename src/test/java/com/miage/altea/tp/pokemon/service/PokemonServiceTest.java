package com.miage.altea.tp.pokemon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.miage.altea.tp.pokemon.repository.PokemonRepository;
import com.miage.altea.tp.pokemon.repository.PokemonRepositoryJson;

public class PokemonServiceTest {
	
	@Test
    void pokemonTypeRepository_shouldBeCalled_whenFindById(){
        var pokemonRepository = mock(PokemonRepository.class); 
        var pokemonService = new PokemonServiceImpl(pokemonRepository); 

        pokemonService.getPokemon(25);

        verify(pokemonRepository).findPokemonById(25);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll(){
        var pokemonRepository = mock(PokemonRepository.class); 
        var pokemonService = new PokemonServiceImpl(pokemonRepository); 

        pokemonService.getAllPokemon();

        verify(pokemonRepository).findAllPokemon();
    }
    
    @SuppressWarnings("resource")
	@Test
    void applicationContext_shouldLoadPokemonTypeService(){
        var context = new AnnotationConfigApplicationContext(PokemonServiceImpl.class, PokemonRepositoryJson.class);
        var serviceByName = context.getBean("pokemonServiceImpl");
        var serviceByClass = context.getBean(PokemonService.class);

        assertEquals(serviceByName, serviceByClass);
        assertNotNull(serviceByName);
        assertNotNull(serviceByClass);
    }

    @SuppressWarnings("resource")
	@Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring(){
        var context = new AnnotationConfigApplicationContext(PokemonServiceImpl.class, PokemonRepositoryJson.class);
        var service = context.getBean(PokemonServiceImpl.class);
        assertNotNull(service.pokemonRepository);
    }

}
