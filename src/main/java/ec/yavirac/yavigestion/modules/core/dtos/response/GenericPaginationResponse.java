package ec.yavirac.yavigestion.modules.core.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Builder
public class GenericPaginationResponse<T> {
    private String message;
    private HttpStatusCode status;
    private T data;
    private int totalPages;
    private int currentPage;
    private int totalElements;
    private int pageSize;
}
