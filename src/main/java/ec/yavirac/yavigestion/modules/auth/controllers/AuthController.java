package ec.yavirac.yavigestion.modules.auth.controllers;

import ec.yavirac.yavigestion.modules.auth.dtos.request.AuthRequest;
import ec.yavirac.yavigestion.modules.auth.dtos.request.RegisterRequest;
import ec.yavirac.yavigestion.modules.auth.dtos.response.AuthResponse;
import ec.yavirac.yavigestion.modules.auth.services.authentication.AuthenticationService;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Log4j2
public class AuthController {
    @Qualifier("authenticationServiceImpl")
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<GenericOnlyTextResponse> register(@RequestBody RegisterRequest req) {
        log.info("Registrando usuario: {}", req.getEmail());
        GenericOnlyTextResponse response = authenticationService.register(req);
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<AuthResponse>> login(@RequestBody AuthRequest req) {
        log.info("El usuario {} esta intentando entrar al sistema", req.getEmail());
        GenericResponse<AuthResponse> response = authenticationService.login(req);

        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }
}