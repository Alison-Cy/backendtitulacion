package ec.yavirac.yavigestion.modules.administration.controllers;

import ec.yavirac.yavigestion.modules.administration.dtos.request.user.CreateUserDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.user.UpdateUserDTO;
import ec.yavirac.yavigestion.modules.administration.dtos.request.user.UserDTO;
import ec.yavirac.yavigestion.modules.administration.services.facades.user.UserFacade;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericOnlyTextResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericPaginationResponse;
import ec.yavirac.yavigestion.modules.core.dtos.response.GenericResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Qualifier("userFacadeImpl")
    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    public ResponseEntity<GenericOnlyTextResponse> create(@RequestBody CreateUserDTO userDTO) {
        GenericOnlyTextResponse response = userFacade.save(userDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<GenericPaginationResponse<UserDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        GenericPaginationResponse<UserDTO> response = userFacade.findAll(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<UserDTO>> findOneById(@PathVariable Long id) {
        GenericResponse<UserDTO> period = userFacade.findById(id);
        return ResponseEntity.status(period.getStatus()).body(period);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericOnlyTextResponse> update(@PathVariable Long id, @RequestBody UpdateUserDTO userDTO) {
        GenericOnlyTextResponse response = userFacade.update(id, userDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericOnlyTextResponse> delete(@PathVariable Long id) {
        GenericOnlyTextResponse response = userFacade.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
