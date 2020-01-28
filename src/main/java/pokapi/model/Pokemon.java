package pokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "Classe représentant un Pokémon")
@MappedSuperclass
public abstract class Pokemon {

    @ApiModelProperty(notes = "Identifiant unique du Pokémon", example = "1", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;

    @ApiModelProperty(notes = "Numéro de Pokédex du pokémon", example = "1")
    @Column(name="id_pokedex")
    protected Long idPokedex;

    @ApiModelProperty(notes = "Nom du pokémon", example = "Bulbizarre")
    @Column(name="name")
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPokedex() {
        return idPokedex;
    }

    public void setIdPokedex(Long idPokedex) {
        this.idPokedex = idPokedex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
