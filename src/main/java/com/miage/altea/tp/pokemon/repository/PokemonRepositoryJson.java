package com.miage.altea.tp.pokemon.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon.bo.Pokemon;

@Repository
public class PokemonRepositoryJson implements PokemonRepository {

	private List<Pokemon> pokemons;

    public PokemonRepositoryJson() {
        try {
            var pokemonsStream = this.getClass().getResourceAsStream("/pokemons.json"); 

            var objectMapper = new ObjectMapper(); 
            var pokemonsArray = objectMapper.readValue(pokemonsStream, Pokemon[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pokemon findPokemonById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        return pokemons.stream().filter(pokemon -> pokemon.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Pokemon> findPokemonByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        return pokemons.stream().filter(pokemon -> pokemon.getName().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Pokemon> findAllPokemon() {
        return pokemons;
    }

}
