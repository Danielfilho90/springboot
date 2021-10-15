package school.cesar.next.project.springboot.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import school.cesar.next.project.springboot.model.abstracts.AbstractNextEntity;

public interface NextRepository<T extends AbstractNextEntity> extends JpaRepository<T, Long> {

}
