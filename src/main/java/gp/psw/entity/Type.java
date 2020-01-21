package gp.psw.entity;

import javax.persistence.*;

@Entity
@Table(name = "TYPE")
public class Type {

    private Long id;
    private String name;

    public Type() {
    }

    public Type(String name) {
        this.name = name;
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
}
