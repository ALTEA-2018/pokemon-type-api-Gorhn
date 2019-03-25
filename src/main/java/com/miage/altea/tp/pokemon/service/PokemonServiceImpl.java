package com.miage.altea.tp.pokemon.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.miage.altea.tp.pokemon.bo.Pokemon;
import com.miage.altea.tp.pokemon.repository.PokemonRepository;
import com.miage.altea.tp.pokemon.repository.TranslationRepository;

@Service
public class PokemonServiceImpl implements PokemonService {
	
	@Autowired
	public PokemonRepository pokemonRepositoryImpl;
	
	@Autowired
	public TranslationRepository translationRepositoryImpl;

	public PokemonServiceImpl() { }
	
	public void setPokemonRepository(PokemonRepository repository) {
		this.pokemonRepositoryImpl = repository;
	}
	
	public void setTranslationRepository(TranslationRepository repository) {
		this.translationRepositoryImpl = repository;
	}

    @Override
    public Pokemon getPokemon(int id) {
    	String name = this.translationRepositoryImpl.getPokemonName(id, LocaleContextHolder.getLocale());
        Pokemon pokemon = this.pokemonRepositoryImpl.findPokemonById(id);

        pokemon.setName(name);
        return pokemon;
    }

    @Override
    public List<Pokemon> getAllPokemon() {
        List<Pokemon> pokemons =  pokemonRepositoryImpl.findAllPokemon();
        return pokemons.stream()
        .map(pokemon -> {
        	String translatedName = translationRepositoryImpl.getPokemonName(pokemon.getId(), LocaleContextHolder.getLocale());
        	System.out.println("Found name ["+translatedName+"] for the locale "+LocaleContextHolder.getLocale());
        	pokemon.setName(translatedName);
        	return pokemon;
        })
        .collect(Collectors.toList());
    }

}
