package pokapi.entity;

import pokapi.model.Type;

import javax.persistence.*;

@Entity
@Table(name = "TYPE")
public class TypeEntity extends Type {

    public TypeEntity() {

    }

    public TypeEntity(Type type){
        id = type.getId();
        name = type.getName();
    }

}
