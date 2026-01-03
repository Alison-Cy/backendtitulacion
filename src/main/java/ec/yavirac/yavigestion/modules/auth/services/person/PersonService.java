package ec.yavirac.yavigestion.modules.auth.services.person;

import ec.yavirac.yavigestion.modules.auth.entities.Person;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    public Person findOneById(Long id);
    public Person save(Person person);
    public Person delete(Long id);
    public Person update(Long id, Person person);
}
