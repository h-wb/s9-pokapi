package pokapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokapi.dto.PokemonDTO;
import pokapi.entity.EstTypeEntity;
import pokapi.entity.PokemonEntity;
import pokapi.entity.TypeEntity;
import pokapi.exception.ResourceNotFoundException;
import pokapi.helper.export.ExportCSV;
import pokapi.helper.export.ExportXLSX;
import pokapi.helper.export.version.PokemonsExportVersion;
import pokapi.helper.export.version.PokemonsExportVersionFull;
import pokapi.helper.export.version.PokemonsExportVersionLight;
import pokapi.helper.search.SearchPokemon;
import pokapi.repository.EstTypeRepository;
import pokapi.repository.PokemonRepository;
import pokapi.repository.TypeRepository;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pokemon")
@Api(value = "Pokémons", tags = "pokemon")
public class PokemonController {
    private final PokemonRepository pokemonRepository;
    private final TypeRepository typeRepository;
    private final EstTypeRepository estTypeRepository;
    private static final String ERROR_POKEMON_ID_NOT_FOUND = "Pokemon not found for this id :: ";

    @Autowired
    public PokemonController(PokemonRepository pokemonRepository, TypeRepository typeRepository, EstTypeRepository estTypeRepository) {
        this.pokemonRepository = pokemonRepository;
        this.typeRepository = typeRepository;
        this.estTypeRepository = estTypeRepository;
    }

    @ApiOperation(value = "Récuperer tous les pokémons")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PokemonEntity> getAllPokemons() {
        return (List<PokemonEntity>) pokemonRepository.findAll();
    }

    @ApiOperation(value = "Récuperer les pokémons par id Pokédex")
    @GetMapping(value = "/pokedex/{IdPokedex}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PokemonEntity> getAllPokemonsByPokedexId(@PathVariable final Long IdPokedex) {
        return ((List<PokemonEntity>) pokemonRepository.findAll()).stream()
                .filter(pokemonEntity -> pokemonEntity.getIdPokedex().equals(IdPokedex))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Créer un pokémon")
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PokemonEntity createPokemon(@Valid @RequestBody PokemonDTO pokemonDTO) {
        return pokemonRepository.save(new PokemonEntity(pokemonDTO));
    }

    @ApiOperation(value = "Récupérer un pokémon par id")
    @GetMapping(value = "/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<PokemonEntity> getPokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_POKEMON_ID_NOT_FOUND + Id));
        return ResponseEntity.ok().body(pokemonEntity);
    }

    @ApiOperation(value = "Supprimer un pokémon par id")
    @DeleteMapping(value = "/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Boolean> deletePokemonById(@PathVariable final Long Id) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_POKEMON_ID_NOT_FOUND + Id));

        pokemonRepository.delete(pokemonEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Modifier un pokémon par id")
    @PutMapping(value = "/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> updatePokemon(@PathVariable final long Id, @Valid @RequestBody PokemonDTO pokemonDTO) throws ResourceNotFoundException {
        PokemonEntity pokemonEntity = pokemonRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_POKEMON_ID_NOT_FOUND + Id));

        pokemonEntity.setName(pokemonDTO.getName());
        pokemonEntity.setIdPokedex(pokemonDTO.getIdPokedex());
        final PokemonEntity updatedPokemonEntity = pokemonRepository.save(pokemonEntity);
        return ResponseEntity.ok(updatedPokemonEntity);
    }

    @ApiOperation(value = "Chercher un Pokémon par nom")
    @GetMapping(value = "/search/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PokemonEntity> searchPokemon(@RequestParam(value = "Name", required = false) String exp) {
        List<PokemonEntity> pokemonEntities = (List<PokemonEntity>) pokemonRepository.findAll();
        return StringUtils.isBlank(exp) ? pokemonEntities : SearchPokemon.searchScore(pokemonEntities, exp);
    }

    @ApiOperation(value = "Exporter des Pokémons par nom")
    @GetMapping(value = "/export/")
    @ResponseBody
    public ResponseEntity<InputStreamResource> exportPokemons(
            @ApiParam(name = "Extension", value = "Format du fichier", allowableValues = "xlsx, csv", required = true) @RequestParam(value = "Extension") String extension,
            @ApiParam(name = "Version", value = "Version à exporter", allowableValues = "full, light", required = true) @RequestParam(value = "Version") String version,
            @RequestParam(value = "Name", required = false) String exp) throws IOException {
        List<PokemonEntity> pokemonEntities = StringUtils.isBlank(exp) ?
                (List<PokemonEntity>) pokemonRepository.findAll() :
                SearchPokemon.searchScore((List<PokemonEntity>) pokemonRepository.findAll(), exp);
        List<TypeEntity> typeEntities = typeRepository.findAll();
        List<EstTypeEntity> estTypeEntities = (List<EstTypeEntity>) estTypeRepository.findAll();

        HashMap<String, String> contentTypes = new HashMap<>();
        contentTypes.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        contentTypes.put("csv", "text/csv; charset=utf-8");

        PokemonsExportVersion pokemonsExportVersion = version.equals("full") ?
                new PokemonsExportVersionFull(extension, pokemonEntities, typeEntities, estTypeEntities) :
                new PokemonsExportVersionLight(extension, pokemonEntities);

        ByteArrayInputStream in = extension.equals("xlsx") ? ExportXLSX.export(pokemonsExportVersion) : ExportCSV.export(pokemonsExportVersion);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
                pokemonsExportVersion.getFileName() +
                (StringUtils.isBlank(exp) ? "" : "_" + exp.trim()) +
                "." + pokemonsExportVersion.getExtension()
        );

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentTypes.get(extension))).headers(headers).body(new InputStreamResource(in));
    }
}
