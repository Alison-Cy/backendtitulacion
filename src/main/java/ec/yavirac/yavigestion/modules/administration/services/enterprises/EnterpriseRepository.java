package ec.yavirac.yavigestion.modules.administration.services.enterprises;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

    Optional<Enterprise> findByRuc(String ruc);

    List<Enterprise> findByActiveTrue();

    List<Enterprise> findByNameContainingIgnoreCaseOrRucContaining(String name, String ruc);
}
