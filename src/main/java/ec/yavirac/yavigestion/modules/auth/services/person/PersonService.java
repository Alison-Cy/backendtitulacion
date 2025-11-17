package ec.yavirac.yavigestion.modules.auth.services.person;

import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public interface PersonService {
    public String getPersonaNameById(Long id);
    public GenericResponse<Object> savePersona(String name);
    public GenericResponse<Object> deletePersona(Long id);
    public GenericResponse<Object> updatePersona(Long id, Object person);



}
