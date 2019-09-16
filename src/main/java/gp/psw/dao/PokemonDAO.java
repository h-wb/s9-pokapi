package gp.psw.dao;

import gp.psw.entity.Pokemon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonDAO extends CrudRepository<Pokemon, Long> {

    public List<Pokemon> findByName(String name);

    public List<Pokemon> findByType(String type);
}
