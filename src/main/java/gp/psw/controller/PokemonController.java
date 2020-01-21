package gp.psw.controller;

import gp.psw.dao.PokemonDAO;
import gp.psw.entity.Pokemon;
import gp.psw.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PokemonController {
    @Autowired
    private PokemonDAO pokemonDAO;

    @GetMapping("/pokemons")
    @ResponseBody
    List<Pokemon> getAllPokemons() {
        return pokemonDAO.findAll();
    }

    @GetMapping("/pokemons/{Id}")
    @ResponseBody
    ResponseEntity<Pokemon> getPokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonDAO.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));
        return ResponseEntity.ok().body(pokemon);
    }

    @DeleteMapping("/pokemons/{Id}")
    @ResponseBody
    Map<String, Boolean> deletePokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonDAO.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemonDAO.delete(pokemon);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/pokemons")
    @ResponseBody
    public Pokemon createPokemon(@Valid @RequestBody Pokemon pokemon) {
        return pokemonDAO.save(pokemon);
    }

    @PutMapping("/pokemons/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updatePokemon(@PathVariable final long Id, @Valid @RequestBody Pokemon pokemonDetails) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonDAO.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemon.setName(pokemonDetails.getName());
        pokemon.setIdPokedex(pokemonDetails.getIdPokedex());
        final Pokemon updatedPokemon = pokemonDAO.save(pokemon);
        return ResponseEntity.ok(updatedPokemon);
    }
}
