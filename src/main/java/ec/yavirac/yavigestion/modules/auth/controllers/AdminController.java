package ec.yavirac.yavigestion.modules.auth.controllers;

import java.util.Optional;

import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import ec.yavirac.yavigestion.modules.auth.entities.Role;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.repositories.PermissionRepository;
import ec.yavirac.yavigestion.modules.auth.repositories.RoleRepository;
import ec.yavirac.yavigestion.modules.auth.repositories.UserRepository;
import ec.yavirac.yavigestion.modules.auth.services.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;
    private final UserRepository userRepo;
    @Qualifier("userServiceImpl")
    private final UserService userService;

    public AdminController(RoleRepository roleRepo, PermissionRepository permissionRepo, UserRepository userRepo, UserService userService) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.userRepo = userRepo;
        this.userService = userService;
    }

    // Crear rol
    @PostMapping("/roles")
    public ResponseEntity<?> createRole(@RequestBody Role role, @RequestAttribute("org.springframework.security.core.context.SecurityContextHolder") Object ignored) {
        User current = (User) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userService.hasPermission(current, "roles:create") && isAdmin(current)) {
            return ResponseEntity.status(403).body("Solo admin o con permiso roles:create");
        }
        if (roleRepo.findByName(role.getName()).isPresent()) return ResponseEntity.badRequest().body("Rol ya existe");
        Role saved = roleRepo.save(role);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/permissions")
    public ResponseEntity<?> createPermission(@RequestBody Permission p) {
        User current = (User) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userService.hasPermission(current, "permissions:create") && isAdmin(current)) {
            return ResponseEntity.status(403).body("Solo admin o con permiso permissions:create");
        }
        if (permissionRepo.findByName(p.getName()).isPresent()) return ResponseEntity.badRequest().body("Permission ya existe");
        Permission saved = permissionRepo.save(p);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/roles/{roleName}/permissions")
    public ResponseEntity<?> addPermissionToRole(@PathVariable String roleName, @RequestParam String permissionName) {
        User current = (User) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userService.hasPermission(current, "roles:assign_permission") && isAdmin(current)) {
            return ResponseEntity.status(403).body("Solo admin o con permiso roles:assign_permission");
        }

        Optional<Role> ro = roleRepo.findByName(roleName);
        if (ro.isEmpty()) return ResponseEntity.status(404).body("Rol no encontrado");
        Role role = ro.get();

        Permission perm = permissionRepo.findByName(permissionName).orElseGet(() -> permissionRepo.save(Permission.builder().name(permissionName).build()));
        role.getPermissions().add(perm);
        roleRepo.save(role);
        return ResponseEntity.ok("Permiso añadido");
    }

    @PostMapping("/users/{userId}/roles")
    public ResponseEntity<?> addRoleToUser(@PathVariable Long userId, @RequestParam String roleName) {
        User current = (User) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userService.hasPermission(current, "users:assign_role") && isAdmin(current)) {
            return ResponseEntity.status(403).body("Solo admin o con permiso users:assign_role");
        }

        Optional<User> ou = userRepo.findById(userId);
        if (ou.isEmpty()) return ResponseEntity.status(404).body("Usuario no encontrado");
        Optional<Role> or = roleRepo.findByName(roleName);
        if (or.isEmpty()) return ResponseEntity.status(404).body("Rol no encontrado");

        User u = ou.get();
        u.getRoles().add(or.get());
        userRepo.save(u);
        return ResponseEntity.ok("Rol asignado");
    }

    private boolean isAdmin(User u) {
        return u.getRoles().stream().map(Role::getName).noneMatch(rn -> rn.equalsIgnoreCase("ROLE_ADMIN") || rn.equalsIgnoreCase("admin"));
    }
}