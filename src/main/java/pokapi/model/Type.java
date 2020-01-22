package pokapi.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(description = "Classe représentant un type de pokémon")
@MappedSuperclass
public abstract class Type {

    @ApiModelProperty(notes = "Identifiant unique d'un type", example = "1", hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;

    @ApiModelProperty(notes = "Nom du type", example = "Feu")
    @Column(name="name")
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
