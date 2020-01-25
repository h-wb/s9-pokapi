package pokapi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokapi.entity.TypeEntity;
import pokapi.exception.ResourceNotFoundException;
import pokapi.repository.TypeRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/type")
@Api(value = "Type", tags = "type", description = "Accès aux types de Pokémons")
public class TypeController {
    private TypeRepository typeRepository;

    @Autowired
    public TypeController(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    @ApiOperation(value= "Récuperer tous les type de pokémons")
    @GetMapping("/all")
    @ResponseBody
    public List<TypeEntity> getAllTypes() {
        return typeRepository.findAll();
    }

    @ApiOperation(value= "Créer un type de pokémon")
    @PostMapping("/new")
    @ResponseBody
    public TypeEntity createType(@Valid @RequestBody TypeEntity typeEntity) {
        return typeRepository.save(typeEntity);
    }

    @ApiOperation(value= "Récupérer un type de pokémon par id")
    @GetMapping("/{Id}")
    @ResponseBody
    public ResponseEntity<TypeEntity> getTypeById(@PathVariable final Long Id) throws ResourceNotFoundException {
        TypeEntity typeEntity = typeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found for this id :: " + Id));
        return ResponseEntity.ok().body(typeEntity);
    }

    @ApiOperation(value= "Supprimer un type de pokémon par id")
    @DeleteMapping("/{Id}")
    @ResponseBody
    public Map<String, Boolean> deleteTypeById(@PathVariable final Long Id) throws ResourceNotFoundException {
        TypeEntity typeEntity = typeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found for this id :: " + Id));

        typeRepository.delete(typeEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @ApiOperation(value= "Modifier un type de pokémon par id")
    @PutMapping("/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updateType(@PathVariable final long Id, @Valid @RequestBody TypeEntity typeEntityDetails) throws ResourceNotFoundException {
        TypeEntity typeEntity = typeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found for this id :: " + Id));

        typeEntity.setName(typeEntityDetails.getName());
        final TypeEntity updatedTypeEntity = typeRepository.save(typeEntity);
        return ResponseEntity.ok(updatedTypeEntity);
    }
}
