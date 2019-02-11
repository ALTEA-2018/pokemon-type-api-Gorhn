package com.miage.altea.tp.pokemon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miage.altea.tp.pokemon.bo.Pokemon;
import com.miage.altea.tp.pokemon.repository.PokemonRepository;

@Service
public class PokemonServiceImpl implements PokemonService {
	
	public PokemonRepository pokemonRepository;

	public PokemonServiceImpl(PokemonRepository repository) {
		pokemonRepository = repository;
    }

    @Override
    public Pokemon getPokemon(int id) {
        return pokemonRepository.findPokemonById(id);
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAllPokemon();
    }

}
