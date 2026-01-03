package ec.yavirac.yavigestion.modules.auth.services.user;

import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import ec.yavirac.yavigestion.modules.auth.entities.Role;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.repositories.UserRepository;
import ec.yavirac.yavigestion.modules.core.consts.StatusConst;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository repo) {
        this.userRepository = repo;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Long count() {
        return this.userRepository.countUserByStatus(StatusConst.ACTIVE);
    }

    public User loadUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
