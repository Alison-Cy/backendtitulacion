package ec.yavirac.yavigestion.modules.auth.services.authentication;

import ec.yavirac.yavigestion.modules.auth.dtos.request.AuthRequest;
import ec.yavirac.yavigestion.modules.auth.dtos.request.RegisterRequest;
import ec.yavirac.yavigestion.modules.auth.dtos.response.AuthResponse;
import ec.yavirac.yavigestion.modules.auth.entities.Person;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.providers.jwt.JwtProvider;
import ec.yavirac.yavigestion.modules.auth.services.person.PersonService;
import ec.yavirac.yavigestion.modules.auth.services.user.UserService;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Qualifier("userServiceImpl")
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    @Qualifier("personServiceImpl")
    private final PersonService personService;

    @Qualifier("jwtProviderImpl")
    private final JwtProvider jwtProvider;

    public AuthenticationServiceImpl(UserService userService, BCryptPasswordEncoder passwordEncoder, PersonService personService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.personService = personService;
        this.jwtProvider = jwtProvider;
    }

    public GenericOnlyTextResponse register(RegisterRequest req) {
        if (userService.findByEmail(req.getEmail()).isPresent()) {
            return GenericOnlyTextResponse.builder()
                            .message("email ya resgistrado")
                            .status(HttpStatus.BAD_REQUEST.value()).build();
        }

        if(!req.getPassword().equals(req.getConfirmPassword())) {
            return GenericOnlyTextResponse.builder()
                    .message("Las contraseñas no coinciden")
                    .status(HttpStatus.BAD_REQUEST.value()).build();
        }


        Person person = new Person();
        person.setName(req.getFirstName());
        person.setLastname(req.getLastName());
        Person createdPerson = personService.save(person);

        User user = User.builder()
                .email(req.getEmail())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .person(createdPerson)
                .status(StatusConst.ACTIVE)
                .build();
        userService.save(user);



        return GenericOnlyTextResponse.builder()
                        .message("Registrado")
                        .status(HttpStatus.CREATED.value()).build();
    }

    public GenericResponse<AuthResponse> login(AuthRequest req) {
        var opt = userService.findByEmail(req.getEmail());
        if (opt.isEmpty()) {
            log.warn("Credenciales incorrectas");
            return GenericResponse.<AuthResponse>builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .message("Credenciales inválidas").build();
        }
        User u = opt.get();
        if (!passwordEncoder.matches(req.getPassword(), u.getPasswordHash())) {
            log.warn("Credenciales incorrectas");

            return GenericResponse.<AuthResponse>builder()
                            .status(HttpStatus.UNAUTHORIZED.value())
                            .message("Credenciales inválidas")
                            .build();
        }
        try {
            log.info("Generando token para el usuario {}", u.getEmail());
            String token = jwtProvider.generateToken(u.getId());
            log.info("Token generado");
            log.info("Usuario autenticado: {}", u.getEmail());
            return GenericResponse.<AuthResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(new AuthResponse(token))
                    .build();
        } catch (Exception e) {
            log.error("Error al generar token", e);
            return GenericResponse.<AuthResponse>builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("Error al generar token").build();
        }
    }

    @Override
    public Object changePassword(String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public Object logout(String token) {
        return null;
    }
}
