package ec.yavirac.yavigestion.modules.auth.services.admin;

import ec.yavirac.yavigestion.modules.auth.dtos.request.role.AssignRoleDTO;
import ec.yavirac.yavigestion.modules.auth.dtos.request.role.RoleDTO;
import ec.yavirac.yavigestion.modules.auth.entities.Permission;
import ec.yavirac.yavigestion.modules.auth.entities.Role;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public interface AdminService {
    GenericPaginationResponse<RoleDTO> findAllRoles(Pageable pageable);
    GenericResponse<Role> createRole(Role role);
    GenericResponse<Permission> createPermission(Permission permission);
    GenericOnlyTextResponse addPermissionToRole(String roleName, String permissionName);

    GenericOnlyTextResponse addRoleToUser(Long userId, AssignRoleDTO roles);
}
