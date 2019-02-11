package com.miage.altea.tp.pokemon.service;

import java.util.List;

import com.miage.altea.tp.pokemon.bo.Pokemon;

public interface PokemonService {

	Pokemon getPokemon(int id);
    List<Pokemon> getAllPokemon();
	
}
