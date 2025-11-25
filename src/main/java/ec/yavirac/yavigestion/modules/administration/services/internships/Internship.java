package ec.yavirac.yavigestion.modules.administration.services.internships;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "internship")
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;          // estudiante asignado
    private Long enterpriseId;       // empresa asignada

    private LocalDate startDate;     // fechade inicio
    private LocalDate endDate;       // fechecha de fin 

    private Integer totalHours;      // horas totales

    private String state;            // REQUESTED, APPROVED, IN_PROGRESS, FINISHED (indica en que etapa se encuentra el estudiante durante el proceso)

    private boolean active = true;   // delete

    private String semester;         // semestre
    private String career;           // carrera

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
