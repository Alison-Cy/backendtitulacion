package ec.yavirac.yavigestion.modules.core.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Builder
public class GenericOnlyTextResponse {
    private String message;
    private int status;
}
