package ec.yavirac.yavigestion.modules.auth.services.authentication;

import ec.yavirac.yavigestion.modules.auth.dtos.request.AuthRequest;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    public Object login(String username, String password);
    public GenericOnlyTextResponse register(AuthRequest request);

    public Object changePassword(String oldPassword, String newPassword);
    public Object logout(String token);
}
