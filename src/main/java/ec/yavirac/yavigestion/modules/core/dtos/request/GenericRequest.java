package ec.yavirac.yavigestion.modules.core.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class GenericRequest<T> {
    private String usrCreacion;
    private String ipCreacion;
    private String method;
    private T payload;
}
