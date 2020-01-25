package pokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "Classe représentant le lien entre un Pokémon et un type")
@MappedSuperclass
public abstract class EstType  {

    @ApiModelProperty(notes = "Identifiant unique du lien entre un Pokémon et un type", example = "1", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;

    @ApiModelProperty(notes = "Identifiant du Pokémon", example = "1")
    @Column(name="id_pokemon")
    protected Long idPokemon;

    @ApiModelProperty(notes = "Identifiant du type", example = "1")
    @Column(name="id_type")
    protected Long idType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Long idPokemon) {
        this.idPokemon = idPokemon;
    }

    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }
}
