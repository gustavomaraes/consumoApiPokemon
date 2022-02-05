package com.gustavo.pokemon.controller;

import com.gustavo.pokemon.banco.Pokemon;
import com.gustavo.pokemon.service.PokemonService;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pokemons")
public class PokemonController {

    PokemonService service;

    public PokemonController() {
        service = new PokemonService();
    }

    @ResponseBody
    @GetMapping
    public JSONObject busca(@RequestParam(name = "pokemon", required = false) String pokemon) {
        return service.buscaPokemons(pokemon);
    }

    @ResponseBody
    @GetMapping(path = "/busca")
    public List<Pokemon> novaBusca(@RequestBody Pokemon payload) {
        return service.novaBuscaPokemons(payload.getName());
    }
}
