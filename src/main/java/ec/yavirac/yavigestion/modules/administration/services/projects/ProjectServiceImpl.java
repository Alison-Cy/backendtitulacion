package ec.yavirac.yavigestion.modules.administration.services.projects;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProjectServiceImpl implements ProjectService{
    @Override
    public void delete(Long id) {
        log.info("Borrando proyecto con id: {}", id);

    }
}
