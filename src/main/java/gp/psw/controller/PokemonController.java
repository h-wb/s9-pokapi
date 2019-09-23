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

    @GetMapping(value = "/pokemon")
    @ResponseBody
    List<Pokemon> getAllPokemons() {
        return pokemonDAO.findAll();
    }

    @GetMapping(value = "/pokemon/{Id}")
    @ResponseBody
    ResponseEntity<Pokemon> getPokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonDAO.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));
        return ResponseEntity.ok().body(pokemon);
    }

    @DeleteMapping(value = "/pokemon/{Id}")
    @ResponseBody
    Map<String, Boolean> deletePokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonDAO.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemonDAO.delete(pokemon);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/pokemon")
    @ResponseBody
    public Pokemon createPokemon(@Valid @RequestBody Pokemon pokemon) {
        return pokemonDAO.save(pokemon);
    }

    @PutMapping("/pokemon/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updatePokemon(@Valid @RequestBody Pokemon pokemonDetails, @PathVariable final long Id) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonDAO.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemon.setName(pokemonDetails.getName());
        final Pokemon updatedPokemon = pokemonDAO.save(pokemon);
        return ResponseEntity.ok(updatedPokemon);
    }
}
