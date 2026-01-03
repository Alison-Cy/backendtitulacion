package ec.yavirac.yavigestion.modules.administration.repositories;

import ec.yavirac.yavigestion.modules.administration.entities.Career;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career,Long > {
    Page<Career> findAll(Pageable pageable);

    Long countCareerByStatus(String status);
}
