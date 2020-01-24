package pokapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    public EstTypeController(EstTypeRepository estTypeRepository) {
        this.estTypeRepository = estTypeRepository;
    }

    @ApiOperation(value = "Récupérer tous les liens entre les Pokémons et les types")
    @GetMapping("/all")
    @ResponseBody
    List<EstTypeEntity> getAllLiensEstType() {
        return (List<EstTypeEntity>) estTypeRepository.findAll();
    }

    @ApiOperation(value = "Récupérer tous les liens entre un Pokémon et ses types par l'id du Pokémon")
    @GetMapping("/all/pokemon/{Id}")
    @ResponseBody
    List<EstTypeEntity> getAllLiensEstTypeByPokemon(@PathVariable final Long Id) {
        List<EstTypeEntity> estTypeEntities = getAllLiensEstType();

        return estTypeEntities.stream()
                .filter(estTypeEntity -> estTypeEntity.getIdPokemon().equals(Id))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Récupérer tous les liens entre les Pokémons et un type par l'id du type")
    @GetMapping("/all/type/{Id}")
    @ResponseBody
    List<EstTypeEntity> getAllLiensEstTypeByType(@PathVariable final Long Id) {
        List<EstTypeEntity> estTypeEntities = getAllLiensEstType();

        return estTypeEntities.stream()
                .filter(estTypeEntity -> estTypeEntity.getIdType().equals(Id))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Créer un lien entre un Pokémon et un type")
    @PostMapping("/new")
    @ResponseBody
    public EstTypeEntity createLienEstType(@Valid @RequestBody EstTypeEntity estTypeEntity) {
        return estTypeRepository.save(estTypeEntity);
    }

    @ApiOperation(value = "Récupérer un lien entre un Pokémon et un type par id")
    @GetMapping("/{Id}")
    @ResponseBody
    ResponseEntity<EstTypeEntity> getLienEstTypeById(@PathVariable final Long Id) throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = estTypeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("EstType not found for this id :: " + Id));
        return ResponseEntity.ok().body(estTypeEntity);
    }

    @ApiOperation(value = "Supprimer un lien entre un Pokémon et un type par id")
    @DeleteMapping("/{Id}")
    @ResponseBody
    Map<String, Boolean> deleteLienEstType(@PathVariable final Long Id) throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = estTypeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("EstType not found for this id :: " + Id));

        estTypeRepository.delete(estTypeEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value = "Modifier un lien entre un Pokémon et un type par id")
    @PutMapping("/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updateLienEstType(@PathVariable final Long Id, @Valid @RequestBody EstTypeEntity estTypeEntityDetails) throws ResourceNotFoundException {
        EstTypeEntity estTypeEntity = estTypeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("EstType not found for this id :: " + Id));

        estTypeEntity.setIdPokemon(estTypeEntityDetails.getIdPokemon());
        estTypeEntity.setIdType(estTypeEntityDetails.getIdType());
        final EstTypeEntity updatedEstTypeEntity = estTypeRepository.save(estTypeEntity);
        return ResponseEntity.ok(updatedEstTypeEntity);
    }
}
