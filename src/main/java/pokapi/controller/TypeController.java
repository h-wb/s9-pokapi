package pokapi.controller;

import pokapi.repository.TypeRepository;
import pokapi.entity.Type;
import pokapi.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TypeController {

    private TypeRepository typeRepository;

    @GetMapping("/types")
    @ResponseBody
    public Iterable<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @GetMapping("/types/{Id}")
    @ResponseBody
    public ResponseEntity<Type> getTypeById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Type type = typeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found for this id :: " + Id));
        return ResponseEntity.ok().body(type);
    }

    @DeleteMapping("/types/{Id}")
    @ResponseBody
    public Map<String, Boolean> deleteTypeById(@PathVariable final Long Id) throws ResourceNotFoundException {
        Type type = typeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found for this id :: " + Id));

        typeRepository.delete(type);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PostMapping("/types")
    @ResponseBody
    public Type createType(@Valid @RequestBody Type type) {
        return typeRepository.save(type);
    }

    @PutMapping("/types/{Id}")
    @ResponseBody
    public ResponseEntity<Object> updateType(@PathVariable final long Id, @Valid @RequestBody Type typeDetails) throws ResourceNotFoundException {
        Type type = typeRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Type not found for this id :: " + Id));

        type.setName(typeDetails.getName());
        final Type updatedType = typeRepository.save(type);
        return ResponseEntity.ok(updatedType);
    }
}
