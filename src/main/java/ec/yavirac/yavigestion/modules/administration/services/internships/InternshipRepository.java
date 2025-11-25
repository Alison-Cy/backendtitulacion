package ec.yavirac.yavigestion.modules.administration.services.internships;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

    // listado de solo activas
    Page<Internship> findByActiveTrue(Pageable pageable);

    // buscar por carrera o semestre
    Page<Internship> findByCareerContainingIgnoreCaseOrSemesterContainingIgnoreCase(
            String career, String semester, Pageable pageable
    );
}
