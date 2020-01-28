package pokapi.entity;

import pokapi.model.Pokemon;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "POKEMON")
public class PokemonEntity extends Pokemon {

    public PokemonEntity() {

    }

    public PokemonEntity(Pokemon pokemon){
        id = pokemon.getId();
        idPokedex = pokemon.getIdPokedex();
        name = pokemon.getName();
    }


}
