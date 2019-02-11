package com.miage.altea.tp.pokemon.repository;

import java.util.List;

import com.miage.altea.tp.pokemon.bo.Pokemon;

public interface PokemonRepository {

	Pokemon findPokemonById(int id);
	List<Pokemon> findPokemonByName(String name);
    List<Pokemon> findAllPokemon();
	
}
