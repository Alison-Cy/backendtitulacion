package ec.yavirac.yavigestion.modules.auth.services.admin;

import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import ec.yavirac.yavigestion.modules.auth.entities.Role;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    GenericResponse<Role> createRole(Role role);
    GenericResponse<Permission> createPermission(Permission permission);
    GenericOnlyTextResponse addPermissionToRole(String roleName, String permissionName);

    GenericOnlyTextResponse addRoleToUser(Long userId, String roleName);
}
