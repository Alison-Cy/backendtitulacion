package ec.yavirac.yavigestion.modules.auth.services.person;

import ec.yavirac.yavigestion.modules.auth.entities.Person;
import ec.yavirac.yavigestion.modules.auth.repositories.PersonRepository;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findOneById(Long id) {
        return personRepository.findOneById(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person delete(Long id) {
        return null;
    }

    @Override
    public Person update(Long id, Person person) {
        return null;
    }
}
