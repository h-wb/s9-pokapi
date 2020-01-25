package pokapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokapi.entity.TypeEntity;

@Repository
public interface TypeRepository extends JpaRepository<TypeEntity, Long> {

}
