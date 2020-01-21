package gp.psw.dao;

import gp.psw.entity.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDAO extends CrudRepository<Type, Long> {

}
