package ec.yavirac.yavigestion.modules.auth.services.authentication;

import ec.yavirac.yavigestion.modules.auth.dtos.request.AuthRequest;
import ec.yavirac.yavigestion.modules.auth.dtos.request.RegisterRequest;
import ec.yavirac.yavigestion.modules.auth.dtos.response.AuthResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public GenericResponse<AuthResponse> login(AuthRequest request);
    public GenericOnlyTextResponse register(RegisterRequest request);

    public Object changePassword(String oldPassword, String newPassword);
    public Object logout(String token);
}
