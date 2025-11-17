package ec.yavirac.yavigestion.modules.auth.controllers;

import java.util.Optional;

import ec.yavirac.yavigestion.modules.auth.decorators.HasPermission;
import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import ec.yavirac.yavigestion.modules.auth.entities.Role;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.repositories.PermissionRepository;
import ec.yavirac.yavigestion.modules.auth.repositories.RoleRepository;
import ec.yavirac.yavigestion.modules.auth.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;
    private final UserRepository userRepo;


    public AdminController(RoleRepository roleRepo, PermissionRepository permissionRepo, UserRepository userRepo) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.userRepo = userRepo;
    }

    // Crear rol
    @PostMapping("/roles")
    @HasPermission("roles:create")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        if (roleRepo.findByName(role.getName()).isPresent()) return ResponseEntity.badRequest().body("Rol ya existe");
        Role saved = roleRepo.save(role);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/permissions")
    @HasPermission("permissions:create")
    public ResponseEntity<?> createPermission(@RequestBody Permission p) {
        if (permissionRepo.findByName(p.getName()).isPresent()) return ResponseEntity.badRequest().body("Permission ya existe");
        Permission saved = permissionRepo.save(p);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/roles/{roleName}/permissions")
    @HasPermission("roles:assign_permission")
    public ResponseEntity<?> addPermissionToRole(@PathVariable String roleName, @RequestParam String permissionName) {

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

        Optional<User> ou = userRepo.findById(userId);
        if (ou.isEmpty()) return ResponseEntity.status(404).body("Usuario no encontrado");
        Optional<Role> or = roleRepo.findByName(roleName);
        if (or.isEmpty()) return ResponseEntity.status(404).body("Rol no encontrado");

        User u = ou.get();
        u.getRoles().add(or.get());
        userRepo.save(u);
        return ResponseEntity.ok("Rol asignado");
    }

}