package pokapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokapi.dto.EstTypeDTO;
import pokapi.entity.EstTypeEntity;
import pokapi.exception.ResourceNotFoundException;
import pokapi.repository.EstTypeRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/esttype")
@Api(value = "EstType", tags = "estType")
public class EstTypeController {
    private final EstTypeRepository estTypeRepository;
    private static final String ERROR_ESTTYPE_ID_NOT_FOUND = "EstType not found for this id :: ";

    @Autowired
    public EstTypeController(EstTypeRepository estTypeRepository) {
        this.estTypeRepository = estTypeRepository;
    }

    @ApiOperation(value = "Récupérer tous les liens entre les Pokémons et les types")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<EstTypeEntity> getAllLiensEstType() {
        return (List<EstTypeEntity>) estTypeRepository.findAll();
    }

    @ApiOperation(value = "Récupérer tous les liens entre un Pokémon et ses types par l'id du Pokémon")
    @GetMapping(value = "/all/pokemon/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<EstTypeEntity> getAllLiensEstTypeByPokemon(@PathVariable final Long Id) {
        List<EstTypeEntity> estTypeEntities = getAllLiensEstType();

        return estTypeEntities.stream()
                .filter(estTypeEntity -> estTypeEntity.getIdPokemon().equals(Id))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Récupérer tous les liens entre les Pokémons et un type par l'id du type")
    @GetMapping(value = "/all/type/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<EstTypeEntity> getAllLiensEstTypeByType(@PathVariable final Long Id) {
        List<EstTypeEntity> estTypeEntities = getAllLiensEstType();

        return estTypeEntities.stream()
                .filter(estTypeEntity -> estTypeEntity.getIdType().equals(Id))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Créer un lien entre un Pokémon et un type")
    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public EstTypeEntity createLienEstType(@Valid @RequestBody EstTypeDTO estTypeDTO) {
        return estTypeRepository.save(new EstTypeEntity(estTypeDTO));
    }

    @ApiOperation(value = "Récupérer un lien entre un Pokémon et un type par id")
    @GetMapping(value = "/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EstTypeEntity> getLienEstTypeById(@PathVariable final Long Id) throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = estTypeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_ESTTYPE_ID_NOT_FOUND + Id));
        return ResponseEntity.ok().body(estTypeEntity);
    }

    @ApiOperation(value = "Supprimer un lien entre un Pokémon et un type par id")
    @DeleteMapping(value = "/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Boolean> deleteLienEstType(@PathVariable final Long Id) throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = estTypeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_ESTTYPE_ID_NOT_FOUND + Id));

        estTypeRepository.delete(estTypeEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Modifier un lien entre un Pokémon et un type par id")
    @PutMapping(value = "/{Id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EstTypeEntity> updateLienEstType(@PathVariable final Long Id, @Valid @RequestBody EstTypeDTO estTypeDTO) throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = estTypeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(ERROR_ESTTYPE_ID_NOT_FOUND + Id));

        estTypeEntity.setIdPokemon(estTypeDTO.getIdPokemon());
        estTypeEntity.setIdType(estTypeDTO.getIdType());
        final EstTypeEntity updatedEstTypeEntity = estTypeRepository.save(estTypeEntity);
        return ResponseEntity.ok(updatedEstTypeEntity);
    }
}
