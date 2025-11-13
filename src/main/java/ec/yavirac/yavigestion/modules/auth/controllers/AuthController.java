package ec.yavirac.yavigestion.modules.auth.controllers;

import ec.yavirac.yavigestion.modules.auth.dtos.request.AuthRequest;
import ec.yavirac.yavigestion.modules.auth.dtos.response.AuthResponse;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.providers.jwt.JwtProvider;
import ec.yavirac.yavigestion.modules.auth.services.user.UserService;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {
    @Qualifier("userServiceImpl")
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Qualifier("jwtProviderImpl")
    private final JwtProvider jwtProvider;

    public AuthController(UserService userService, BCryptPasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<GenericOnlyTextResponse> register(@RequestBody AuthRequest req) {
        if (userService.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(GenericOnlyTextResponse.builder()
                            .message("email ya resgistrado")
                            .status(HttpStatus.BAD_REQUEST.value()).build());
        }
        User u = User.builder()
                .email(req.getEmail())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .build();
        userService.save(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GenericOnlyTextResponse.builder()
                        .message("Registrado")
                        .status(HttpStatus.CREATED.value()).build());
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<AuthResponse>> login(@RequestBody AuthRequest req) {
        var opt = userService.findByEmail(req.getEmail());
        if (opt.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(GenericResponse.<AuthResponse>builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .message("Credenciales inválidas").build());
        User u = opt.get();
        if (!passwordEncoder.matches(req.getPassword(), u.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(GenericResponse.<AuthResponse>builder()
                            .status(HttpStatus.UNAUTHORIZED.value())
                            .message("Credenciales inválidas")
                            .build());
        }
        try {
            String token = jwtProvider.generateToken(u.getId());
            return ResponseEntity.ok(GenericResponse.<AuthResponse>builder()
                    .status(HttpStatus.OK.value())
                    .data(new AuthResponse(token))
                    .build());
        } catch (Exception e) {
            log.error("Error al generar token", e);
            return ResponseEntity.status(500).body( GenericResponse.<AuthResponse>builder().status(HttpStatus.BAD_REQUEST.value()).message("Error al generar token").build());
        }
    }
}