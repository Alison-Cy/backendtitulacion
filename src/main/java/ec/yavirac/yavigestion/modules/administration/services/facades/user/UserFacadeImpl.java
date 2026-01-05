package ec.yavirac.yavigestion.modules.administration.services.facades.user;

import ec.yavirac.yavigestion.modules.administration.dtos.request.user.CreateUserDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.user.UpdateUserDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.user.UserDTO;
import ec.yavirac.yavigestion.modules.auth.dtos.request.PersonDto;
import ec.yavirac.yavigestion.modules.auth.dtos.request.role.RoleDTO;
import ec.yavirac.yavigestion.modules.auth.entities.Person;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.services.person.PersonService;
import ec.yavirac.yavigestion.modules.auth.services.user.UserService;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
public class UserFacadeImpl implements UserFacade {

    @Qualifier("userServiceImpl")
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Qualifier("personServiceImpl")
    private final PersonService personService;


    public UserFacadeImpl(UserService userService, BCryptPasswordEncoder passwordEncoder, PersonService personService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.personService = personService;
    }

    @Override
    public GenericOnlyTextResponse save(CreateUserDTO user) {
        log.info("UserFacadeImpl::save");
        log.info("Buscando usuario con el email: {}", user.getEmail());
        Optional<User> foundedUser = userService.findByEmail(user.getEmail());

        if (foundedUser.isPresent()) {
            return GenericOnlyTextResponse.builder().message("El usuario ya existe").status(400).build();
        }

        log.info("Creando la persona del email: {}", user.getEmail());
        Person person = getPerson(user);
        Person savedPerson = personService.save(person);

        log.info("Creando el usuario");
        User entity = new User();
        entity.setPerson(savedPerson);
        entity.setPasswordHash(passwordEncoder.encode(user.getPassword()));
        entity.setEmail(user.getEmail());
        entity.setStatus(user.getStatus());
        User careers = userService.save(entity);

        if (careers == null) {
            return GenericOnlyTextResponse.builder().message("No se pudo guardar el usuario").status(500).build();
        }

        log.info("Se guardo el usuario");
        return GenericOnlyTextResponse.builder()
                .status(201)
                .message("Se ha creado exitosamente").build();
    }


    @Override
    public GenericOnlyTextResponse update(Long id, UpdateUserDTO user) {
        log.info("UserFacadeImpl::update");

        log.info("Actualizando la persona del email: {}", user.getEmail());
        Person foundedPerson = personService.findOneById(user.getPerson().getId());

        if (foundedPerson == null) {
            foundedPerson = new Person();
        }

        Person person = getUpdatePerson(user, foundedPerson);
        Person savedPerson = personService.save(person);

        log.info("Actualizando el usuario");
        User entity = userService.findById(id);
        entity.setPerson(savedPerson);
        entity.setEmail(user.getEmail());
        entity.setStatus(user.getStatus());
        User careers = userService.save(entity);

        if (careers == null) {
            return GenericOnlyTextResponse.builder().message("No se pudo guardar el usuario").status(500).build();
        }

        log.info("Se guardo el usuario");
        return GenericOnlyTextResponse.builder()
                .status(201)
                .message("Se ha actualizado exitosamente").build();    }

    @Override
    public GenericOnlyTextResponse delete(Long id) {
        userService.delete(id);
        return GenericOnlyTextResponse.builder().status(203).message("Se ha eliminado el periodo").build();    }

    @Override
    public GenericResponse<UserDTO> findById(Long id) {
        User entity = userService.findById(id);
        UserDTO transformedUser = toDTO(entity);
        return GenericResponse.<UserDTO>builder()
                .message("Se ha encontrado el registro")
                .data(transformedUser)
                .status(200)
                .build();
    }

    @Override
    public GenericPaginationResponse<UserDTO> findAll(Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        List<UserDTO> transformedPeriods = page.getContent().stream()
                .map(this::toDTO).toList();

        return ResponseEntity.ok(GenericPaginationResponse
                .<UserDTO>builder()
                .currentPage(pageable.getPageNumber())
                .data(transformedPeriods)
                .totalPages(page.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(page.getTotalElements())
                .status(200)
                .build()).getBody();
    }

    private static Person getPerson(CreateUserDTO user) {
        PersonDto personRequest = user.getPerson();
        Person person = new Person();
        person.setBloodtype(personRequest.getBloodtype());
        person.setName(personRequest.getName());
        person.setAddress(personRequest.getAddress());
        person.setDni(personRequest.getDni());
        person.setPhonenumber(personRequest.getPhonenumber());
        person.setGender(personRequest.getGender());
        person.setLastname(personRequest.getLastname());
        person.setStatus(personRequest.getStatus());
        person.setBirthdate(personRequest.getBirthdate());
        person.setEmail(personRequest.getEmail());
        return person;
    }

    private static Person getUpdatePerson(UpdateUserDTO user, Person updatedPerson) {
        PersonDto personRequest = user.getPerson();
        updatedPerson.setName(personRequest.getName());
        updatedPerson.setBloodtype(personRequest.getBloodtype());
        updatedPerson.setAddress(personRequest.getAddress());
        updatedPerson.setDni(personRequest.getDni());
        updatedPerson.setPhonenumber(personRequest.getPhonenumber());
        updatedPerson.setGender(personRequest.getGender());
        updatedPerson.setLastname(personRequest.getLastname());
        updatedPerson.setStatus(personRequest.getStatus());
        updatedPerson.setBirthdate(personRequest.getBirthdate());
        updatedPerson.setEmail(personRequest.getEmail());
        return updatedPerson;
    }

    private UserDTO toDTO(User user) {
        PersonDto personDto = PersonDto.builder().build();
        if(user.getPerson() != null) {
            personDto = PersonDto
                    .builder()
                    .dni(user.getPerson().getDni())
                    .name(user.getPerson().getName())
                    .address(user.getPerson().getAddress())
                    .gender(user.getPerson().getGender())
                    .lastname(user.getPerson().getLastname())
                    .phonenumber(user.getPerson().getPhonenumber())
                    .bloodtype(user.getPerson().getBloodtype())
                    .birthdate(user.getPerson().getBirthdate())
                    .address(user.getPerson().getAddress())
                    .email(user.getPerson().getEmail())
                    .status(user.getPerson().getStatus())
                    .id(user.getId())
                    .build();
        }
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(user.getRoles()
                        .stream().
                        map(role -> RoleDTO
                                .builder()
                                .id(role.getId())
                                .name(role.getName())
                                .description(role.getDescription())
                                .build())
                        .collect(Collectors.toSet()))
                .person(personDto)
                .status(user.getStatus())
                .build();
    }
}
