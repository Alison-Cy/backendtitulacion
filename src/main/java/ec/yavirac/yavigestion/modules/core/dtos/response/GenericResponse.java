package ec.yavirac.yavigestion.modules.core.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Builder
public class GenericResponse<T> {
    private String message;
    private int status;
    private T data;
}
