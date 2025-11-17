package ec.yavirac.yavigestion.modules.auth.services.user;

import ec.yavirac.yavigestion.modules.auth.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public interface UserService {
    public User loadUserById(Long id);
    public User save(User u);
    public Optional<User> findByEmail(String email);

}
