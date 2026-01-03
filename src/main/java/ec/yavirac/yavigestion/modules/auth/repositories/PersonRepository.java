package ec.yavirac.yavigestion.modules.auth.repositories;

import ec.yavirac.yavigestion.modules.auth.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findOneById(Long id);

}
