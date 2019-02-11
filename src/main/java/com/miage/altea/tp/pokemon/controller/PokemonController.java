package com.miage.altea.tp.pokemon.controller;

import java.util.List;

import com.miage.altea.tp.pokemon.bo.Pokemon;

public interface PokemonController {

    Pokemon getPokemonFromId(int id);
    public List<Pokemon> getAllPokemon();
	
}
