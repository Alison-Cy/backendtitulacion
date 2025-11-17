package ec.yavirac.yavigestion.modules.core.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String titulo;
    private String mensaje;
    private Object detalles;
    private LocalDateTime timestamp;

    public ErrorResponse(String titulo, String mensaje, Object detalles) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.detalles = detalles;
        this.timestamp = LocalDateTime.now();
    }
    public ErrorResponse(String titulo, String mensaje) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.detalles = null;
        this.timestamp = LocalDateTime.now();
    }
}