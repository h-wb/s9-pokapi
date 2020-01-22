package pokapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "POKEMON")
public class Pokemon {

    private Long id;
    private String name;
    private Long idPokedex;

    public Pokemon() {
    }

    public Pokemon(String name, Long idPokedex) {
        this.name = name;
        this.idPokedex = idPokedex;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "id_pokedex", nullable = false)
    public Long getIdPokedex() {
        return idPokedex;
    }

    public void setIdPokedex(Long idPokedex) {
        this.idPokedex = idPokedex;
    }
}
