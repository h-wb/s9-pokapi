package gp.psw.entity;

import javax.persistence.*;

@Entity
@Table(name = "POKEMON")
public class Pokemon {

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPokedexId() {
        return pokedexId;
    }

    public void setPokedexId(Integer pokedexId) {
        this.pokedexId = pokedexId;
    }

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "pokedexId", nullable = false)
    private Integer pokedexId;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "type", length = 64, nullable = false)
    private String type;
}
