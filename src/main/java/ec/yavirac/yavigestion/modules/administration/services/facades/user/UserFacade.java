package ec.yavirac.yavigestion.modules.administration.services.facades.user;

import ec.yavirac.yavigestion.modules.administration.dtos.request.user.CreateUserDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.user.UpdateUserDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.user.UserDTO;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserFacade {
    GenericOnlyTextResponse save(CreateUserDTO career);

    GenericOnlyTextResponse update(Long id, UpdateUserDTO career);

    GenericOnlyTextResponse delete(Long id);

    GenericResponse<UserDTO> findById(Long id);

    GenericPaginationResponse<UserDTO> findAll(Pageable pageable);
}
