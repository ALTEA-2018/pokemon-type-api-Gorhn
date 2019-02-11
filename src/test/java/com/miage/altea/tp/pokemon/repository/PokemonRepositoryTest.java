package com.miage.altea.tp.pokemon.repository;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class PokemonRepositoryTest {

    private PokemonRepository repository = new PokemonRepositoryJson();
    
    @SuppressWarnings("resource")
	@Test
    void applicationContext_shouldLoadPokemonRepository(){
        
        var context = new AnnotationConfigApplicationContext("com.miage.altea.tp.pokemon.repository");
        var repoByName = context.getBean("pokemonRepositoryJson"); 
        var repoByClass = context.getBean(PokemonRepository.class); 

        assertEquals(repoByName, repoByClass);
        assertNotNull(repoByName);
        assertNotNull(repoByClass);
    }

    @Test
    void findPokemonTypeById_with25_shouldReturnPikachu(){
        var pikachu = repository.findPokemonById(25);
        assertNotNull(pikachu);
        assertEquals("pikachu", pikachu.getName());
        assertEquals(25, pikachu.getId());
    }

    @Test
    void findPokemonTypeById_with145_shouldReturnZapdos(){
        var zapdos = repository.findPokemonById(145);
        assertNotNull(zapdos);
        assertEquals("zapdos", zapdos.getName());
        assertEquals(145, zapdos.getId());
    }

    @Test
    void findPokemonTypeByName_withEevee_shouldReturnEevee(){
        var pkmLikeEevee = repository.findPokemonByName("eve");
        assertNotNull(pkmLikeEevee);
        assertNotNull(pkmLikeEevee.stream().filter(pokemon -> "eevee".equals(pokemon.getName())).findFirst().orElse(null));
        assertNotNull(pkmLikeEevee.stream().filter(pokemon -> 133 == pokemon.getId()).findFirst().orElse(null));
    }

    @Test
    void findPokemonTypeByName_withMewTwo_shouldReturnMewTwo(){
        var pkmLikeMewtwo = repository.findPokemonByName("mewtwo");
        assertNotNull(pkmLikeMewtwo);
        assertNotNull(pkmLikeMewtwo.stream().filter(pokemon -> "mewtwo".equals(pokemon.getName())).findFirst().orElse(null));
        assertNotNull(pkmLikeMewtwo.stream().filter(pokemon -> 150 == pokemon.getId()).findFirst().orElse(null));
    }

    @Test
    void findAllPokemonType_shouldReturn151Pokemons(){
        var pokemons = repository.findAllPokemon();
        assertNotNull(pokemons);
        assertEquals(151, pokemons.size());
    }

}