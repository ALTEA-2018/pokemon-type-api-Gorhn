package com.miage.altea.tp.pokemon.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miage.altea.tp.pokemon.bo.Pokemon;
import com.miage.altea.tp.pokemon.service.PokemonService;

@RestController
@RequestMapping("/pokemon")
public class PokemonControllerImpl implements PokemonController {

	public PokemonService service;
	
	public PokemonControllerImpl(PokemonService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	@Override
	public Pokemon getPokemonFromId(@PathVariable int id) {
		return service.getPokemon(id);
	}

	@GetMapping("/")
	@Override
	public List<Pokemon> getAllPokemon() {
		return service.getAllPokemon();
	}
	
}
