package pokapi.repository;

import org.springframework.data.repository.CrudRepository;
import pokapi.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CrudRepository<PokemonEntity, Long> {

}
