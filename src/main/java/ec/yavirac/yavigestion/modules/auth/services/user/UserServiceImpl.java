package ec.yavirac.yavigestion.modules.auth.services.user;

import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import ec.yavirac.yavigestion.modules.auth.entities.Role;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.repositories.UserRepository;
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

    public User save(User u) {
        return userRepository.save(u);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User loadUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene permisos calculados del usuario. Si el usuario tiene ROLE_ADMIN (por nombre),
     * devolvemos null o un set especial para indicar "todos" (bypass lógico).
     */
    public Set<String> getEffectivePermissions(User user) {
        if (user == null) return Set.of();
        // si tiene rol admin devolvemos un set con "*" para indicar que tiene TODO
        boolean isAdmin = user.getRoles().stream()
                .map(Role::getName)
                .anyMatch(rn -> rn.equalsIgnoreCase("ROLE_ADMIN") || rn.equalsIgnoreCase("admin"));

        if (isAdmin) {
            return Set.of("*"); // indicador: tiene todos los permisos
        }

        return user.getRoles().stream()
                .flatMap(r -> {
                    Set<Permission> perms = r.getPermissions();
                    if (perms == null) return java.util.stream.Stream.empty();
                    return perms.stream();
                })
                .map(Permission::getName)
                .collect(Collectors.toSet());
    }

    public boolean hasPermission(User user, String permission) {
        Set<String> effective = getEffectivePermissions(user);
        if (effective.contains("*")) return true; // admin bypass
        return effective.contains(permission);
    }
}
