package pokapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import pokapi.entity.PokemonEntity;
import pokapi.repository.PokemonRepository;
import pokapi.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pokemon")
@Api(value = "Pokémons", tags = "pokemon")
public class PokemonController {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonController(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    @ApiOperation(value= "Récuperer tous les pokémons")
    @GetMapping("/all")
    @ResponseBody
    List<PokemonEntity> getAllPokemons() {
        return (List<PokemonEntity>) pokemonRepository.findAll();
    }

    @ApiOperation(value= "Récuperer les pokémons par id Pokédex")
    @GetMapping("/pokedex/{IdPokedex}")
    @ResponseBody
    List<PokemonEntity> getAllPokemonsByPokedexId(@PathVariable final Long IdPokedex) {
        return ((List<PokemonEntity>) pokemonRepository.findAll()).stream()
                .filter(pokemonEntity -> pokemonEntity.getIdPokedex().equals(IdPokedex))
                .collect(Collectors.toList());
    }

    @ApiOperation(value= "Créer un pokémon")
    @PostMapping("/new")
    @ResponseBody
    public PokemonEntity createPokemon(@Valid @RequestBody PokemonEntity pokemonEntity) {
        return pokemonRepository.save(pokemonEntity);
    }

    @ApiOperation(value= "Récupérer un pokémon par id")
    @GetMapping("/{Id}")
    @ResponseBody
    ResponseEntity<PokemonEntity> getPokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));
        return ResponseEntity.ok().body(pokemonEntity);
    }

    @ApiOperation(value= "Supprimer un pokémon par id")
    @DeleteMapping("/{Id}")
    @ResponseBody
    Map<String, Boolean> deletePokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemonRepository.delete(pokemonEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value= "Modifier un pokémon par id")
    @PutMapping("/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updatePokemon(@PathVariable final long Id, @Valid @RequestBody PokemonEntity pokemonEntityDetails) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Pokemon not found for this id :: " + Id));

        pokemonEntity.setName(pokemonEntityDetails.getName());
        pokemonEntity.setIdPokedex(pokemonEntityDetails.getIdPokedex());
        final PokemonEntity updatedPokemonEntity = pokemonRepository.save(pokemonEntity);
        return ResponseEntity.ok(updatedPokemonEntity);
    }

}
