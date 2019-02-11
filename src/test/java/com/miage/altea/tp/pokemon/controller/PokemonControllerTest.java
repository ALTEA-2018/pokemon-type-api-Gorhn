package com.miage.altea.tp.pokemon.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.miage.altea.tp.pokemon.bo.Pokemon;
import com.miage.altea.tp.pokemon.service.PokemonService;

public class PokemonControllerTest {

	@Test
    void getPokemonType_shouldCallTheService(){
        var service = mock(PokemonService.class);
        var controller = new PokemonControllerImpl(service);

        var pikachu = new Pokemon();
        pikachu.setId(25);
        pikachu.setName("pikachu");
        when(service.getPokemon(25)).thenReturn(pikachu);

        var pokemon = controller.getPokemonFromId(25);
        assertEquals("pikachu", pokemon.getName());

        verify(service).getPokemon(25);
    }

    @Test
    void getAllPokemonTypes_shouldCallTheService(){
        var service = mock(PokemonService.class);
        var controller = new PokemonControllerImpl(service);

        controller.getAllPokemon();

        verify(service).getAllPokemon();
    }
    
    @Test
    void pokemonTypeController_shouldBeAnnotated(){
        var controllerAnnotation =
                PokemonControllerImpl.class.getAnnotation(RestController.class);
        assertNotNull(controllerAnnotation);

        var requestMappingAnnotation =
        		PokemonControllerImpl.class.getAnnotation(RequestMapping.class);
        assertArrayEquals(new String[]{"/pokemon"}, requestMappingAnnotation.value());
    }

    @Test
    void getPokemonTypeFromId_shouldBeAnnotated() throws NoSuchMethodException {
        var getPokemonTypeFromId =
        		PokemonControllerImpl.class.getDeclaredMethod("getPokemonFromId", int.class);
        var getMapping = getPokemonTypeFromId.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/{id}"}, getMapping.value());
    }

    @Test
    void getAllPokemonTypes_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllPokemonTypes =
        		PokemonControllerImpl.class.getDeclaredMethod("getAllPokemon");
        var getMapping = getAllPokemonTypes.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }
	
}
