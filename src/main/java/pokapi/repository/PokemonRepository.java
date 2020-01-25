package pokapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pokapi.entity.PokemonEntity;

@Repository
public interface PokemonRepository extends CrudRepository<PokemonEntity, Long> {

}
