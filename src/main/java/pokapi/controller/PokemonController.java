package pokapi.controller;

import pokapi.repository.PokemonRepository;
import pokapi.entity.Pokemon;
import pokapi.exception.ResourceNotFoundException;
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
    private PokemonRepository pokemonRepository;

    @GetMapping("/pokemons")
    @ResponseBody
    List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    @GetMapping("/pokemons/{Id}")
    @ResponseBody
    ResponseEntity<Pokemon> getPokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));
        return ResponseEntity.ok().body(pokemon);
    }

    @DeleteMapping("/pokemons/{Id}")
    @ResponseBody
    Map<String, Boolean> deletePokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemonRepository.delete(pokemon);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/pokemons")
    @ResponseBody
    public Pokemon createPokemon(@Valid @RequestBody Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    @PutMapping("/pokemons/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updatePokemon(@PathVariable final long Id, @Valid @RequestBody Pokemon pokemonDetails) throws ResourceNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemon.setName(pokemonDetails.getName());
        pokemon.setIdPokedex(pokemonDetails.getIdPokedex());
        final Pokemon updatedPokemon = pokemonRepository.save(pokemon);
        return ResponseEntity.ok(updatedPokemon);
    }
}
