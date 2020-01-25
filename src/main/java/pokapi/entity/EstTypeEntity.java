package pokapi.entity;

import pokapi.model.EstType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ESTTYPE")
public class EstTypeEntity extends EstType {

    public EstTypeEntity() {

    }

    public EstTypeEntity(EstType estType) {
        id = estType.getId();
        idPokemon = estType.getIdPokemon();
        idType = estType.getIdType();
    }
}
