package school.cesar.next.project.springboot.repository;

import school.cesar.next.project.springboot.model.Aluno;
import school.cesar.next.project.springboot.repository.interfaces.NextRepository;
import java.util.Set;

public interface AlunoRepository extends NextRepository<Aluno> {

    Set<Aluno> findByNomeIgnoreCaseOrderByNomeAsc(final String nome);

    Set<Aluno> findByNomeIgnoreCaseContainingOrderByNomeAsc(final String nome);

}
