package ec.yavirac.yavigestion.modules.core.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
@Setter
@Builder
public class GenericPaginationResponse<T> {
    private String message;
    private int status;
    private List<T> data;
    private int totalPages;
    private int currentPage;
    private long totalElements;
    private int pageSize;
}
