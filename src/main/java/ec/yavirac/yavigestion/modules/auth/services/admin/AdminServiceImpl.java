package ec.yavirac.yavigestion.modules.auth.services.admin;

import ec.yavirac.yavigestion.modules.auth.decorators.HasPermission;
import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import ec.yavirac.yavigestion.modules.auth.entities.Person;
import ec.yavirac.yavigestion.modules.auth.entities.Role;
import ec.yavirac.yavigestion.modules.auth.entities.User;
import ec.yavirac.yavigestion.modules.auth.repositories.PermissionRepository;
import ec.yavirac.yavigestion.modules.auth.repositories.RoleRepository;
import ec.yavirac.yavigestion.modules.auth.repositories.UserRepository;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class AdminServiceImpl implements AdminService {
    private final RoleRepository roleRepo;
    private final PermissionRepository permissionRepo;
    private final UserRepository userRepo;


    public AdminServiceImpl(RoleRepository roleRepo, PermissionRepository permissionRepo, UserRepository userRepo) {
        this.roleRepo = roleRepo;
        this.permissionRepo = permissionRepo;
        this.userRepo = userRepo;
    }                                                                                                                                   
    public GenericResponse<Role> createRole(Role role) {
        if (roleRepo.findByName(role.getName()).isPresent()) return GenericResponse.<Role>builder()
                .message("El rol que se desea crear ya existe")
                .status(400)
                .build();
        Role saved = roleRepo.save(role);
        return GenericResponse.<Role>builder()
                .status(201)
                .message("Rol creado correctamente")
                .data(saved).build();
    }
    
    public GenericResponse<Permission> createPermission(Permission permission) {
        if (permissionRepo.findByName(permission.getName()).isPresent()) return GenericResponse.<Permission>builder()
                .status(400)
                .message("El permiso que desea crear ya existe")
                .build();
        Permission saved = permissionRepo.save(permission);
        return GenericResponse.<Permission>builder()
                .status(201)
                .message("Permiso creado correctamente")
                .data(saved)
                .build();
    }


    public GenericOnlyTextResponse addPermissionToRole(String roleName, String permissionName) {

        Optional<Role> ro = roleRepo.findByName(roleName);
        if (ro.isEmpty()) return GenericOnlyTextResponse.builder().status(404).message("Rol no encontrado").build();
        Role role = ro.get();

        Permission perm = permissionRepo.findByName(permissionName)
                .orElseGet(() -> permissionRepo.save(Permission.builder().name(permissionName).build()));
        role.getPermissions().add(perm);
        roleRepo.save(role);
        return GenericOnlyTextResponse.builder()
                .message("Permiso añadido")
                .status(200)
                .build();
    }

    public GenericOnlyTextResponse addRoleToUser(Long userId, String roleName) {
        Optional<User> ou = userRepo.findById(userId);
        if (ou.isEmpty()) return GenericOnlyTextResponse.builder().status(404).message("Usuario no encontrado").build();
        Optional<Role> or = roleRepo.findByName(roleName);
        if (or.isEmpty()) return GenericOnlyTextResponse.builder().status(404).message("Rol no encontrado").build();

        User u = ou.get();
        u.getRoles().add(or.get());
        userRepo.save(u);
        return GenericOnlyTextResponse.builder().status(200).message("Rol asignado").build();
    }
}
