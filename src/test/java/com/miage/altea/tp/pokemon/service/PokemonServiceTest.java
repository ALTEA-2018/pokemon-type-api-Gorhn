package com.miage.altea.tp.pokemon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

import com.miage.altea.tp.pokemon.bo.Pokemon;
import com.miage.altea.tp.pokemon.repository.PokemonRepository;
import com.miage.altea.tp.pokemon.repository.PokemonRepositoryJson;
import com.miage.altea.tp.pokemon.repository.TranslationRepository;
import com.miage.altea.tp.pokemon.repository.TranslationRepositoryImpl;

public class PokemonServiceTest {

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll(){
        var pokemonRepository = mock(PokemonRepository.class); 
        var pokemonService = new PokemonServiceImpl(); 
        pokemonService.setPokemonRepository(pokemonRepository);

        pokemonService.getAllPokemon();

        verify(pokemonRepository).findAllPokemon();
    }
    
    @SuppressWarnings("resource")
	@Test
    void applicationContext_shouldLoadPokemonTypeService(){
        var context = new AnnotationConfigApplicationContext(PokemonServiceImpl.class, PokemonRepositoryJson.class, TranslationRepositoryImpl.class);
        var serviceByName = context.getBean("pokemonServiceImpl");
        var serviceByClass = context.getBean(PokemonService.class);

        assertEquals(serviceByName, serviceByClass);
        assertNotNull(serviceByName);
        assertNotNull(serviceByClass);
    }

    @SuppressWarnings("resource")
	@Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring(){
        var context = new AnnotationConfigApplicationContext(PokemonServiceImpl.class, PokemonRepositoryJson.class, TranslationRepositoryImpl.class);
        var service = context.getBean(PokemonServiceImpl.class);
        assertNotNull(service.pokemonRepositoryImpl);
    }

    @Test
    void pokemonNames_shouldBeTranslated_usingLocaleResolver(){

        var pokemonTypeService = new PokemonServiceImpl();
        
        var pokemonTypeRepository = mock(PokemonRepository.class);
        pokemonTypeService.setPokemonRepository(pokemonTypeRepository);
        when(pokemonTypeRepository.findPokemonById(25)).thenReturn(new Pokemon());

        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pikachu = pokemonTypeService.getPokemon(25);

        assertEquals("Pikachu-FRENCH", pikachu.getName());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
    }

    @Test
    void allPokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeService = new PokemonServiceImpl();

        var pokemonTypeRepository = mock(PokemonRepository.class);
        pokemonTypeService.setPokemonRepository(pokemonTypeRepository);
        when(pokemonTypeRepository.findPokemonById(25)).thenReturn(new Pokemon());

        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pikachu = pokemonTypeService.getPokemon(25);

        assertEquals("Pikachu-FRENCH", pikachu.getName());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
    }
    
}
