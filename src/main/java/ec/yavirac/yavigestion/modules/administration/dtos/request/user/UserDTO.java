package ec.yavirac.yavigestion.modules.administration.dtos.request.user;

import ec.yavirac.yavigestion.modules.auth.dtos.request.PersonDto;
import ec.yavirac.yavigestion.modules.auth.dtos.request.role.RoleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private Instant createdAt;
    private Set<RoleDTO> roles;
    private PersonDto person ;
    private String status;
}
