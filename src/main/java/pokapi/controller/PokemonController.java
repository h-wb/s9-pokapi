package pokapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokapi.dto.PokemonDTO;
import pokapi.entity.PokemonEntity;
import pokapi.exception.ResourceNotFoundException;
import pokapi.repository.PokemonRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pokemon")
@Api(value = "Pokémons", tags = "pokemon", description = "Accès aux Pokémons")
public class PokemonController {
    private final PokemonRepository pokemonRepository;
    private static final String ERROR_POKEMON_ID_NOT_FOUND = "Pokemon not found for this id :: ";

    @Autowired
    public PokemonController(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }

    @ApiOperation(value= "Récuperer tous les pokémons")
    @GetMapping("/all")
    @ResponseBody
    public List<PokemonEntity> getAllPokemons() {
        return (List<PokemonEntity>) pokemonRepository.findAll();
    }

    @ApiOperation(value= "Récuperer les pokémons par id Pokédex")
    @GetMapping("/pokedex/{IdPokedex}")
    @ResponseBody
    public List<PokemonEntity> getAllPokemonsByPokedexId(@PathVariable final Long IdPokedex) {
        return ((List<PokemonEntity>) pokemonRepository.findAll()).stream()
                .filter(pokemonEntity -> pokemonEntity.getIdPokedex().equals(IdPokedex))
                .collect(Collectors.toList());
    }

    @ApiOperation(value= "Créer un pokémon")
    @PostMapping("/new")
    @ResponseBody
    public PokemonEntity createPokemon(@Valid @RequestBody PokemonDTO pokemonDTO) {
        return pokemonRepository.save(new PokemonEntity(pokemonDTO));
    }

    @ApiOperation(value= "Récupérer un pokémon par id")
    @GetMapping("/{Id}")
    @ResponseBody
    public ResponseEntity<PokemonEntity> getPokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_POKEMON_ID_NOT_FOUND + Id));
        return ResponseEntity.ok().body(pokemonEntity);
    }

    @ApiOperation(value= "Supprimer un pokémon par id")
    @DeleteMapping("/{Id}")
    @ResponseBody
    public Map<String, Boolean> deletePokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_POKEMON_ID_NOT_FOUND + Id));

        pokemonRepository.delete(pokemonEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value= "Modifier un pokémon par id")
    @PutMapping("/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updatePokemon(@PathVariable final long Id, @Valid @RequestBody PokemonDTO pokemonDTO) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_POKEMON_ID_NOT_FOUND + Id));

        pokemonEntity.setName(pokemonDTO.getName());
        pokemonEntity.setIdPokedex(pokemonDTO.getIdPokedex());
        final PokemonEntity updatedPokemonEntity = pokemonRepository.save(pokemonEntity);
        return ResponseEntity.ok(updatedPokemonEntity);
    }

}
