package school.cesar.next.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import school.cesar.next.project.springboot.exception.AlunoMesmoCPFException;
import school.cesar.next.project.springboot.model.Aluno;
import school.cesar.next.project.springboot.repository.AlunoRepository;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(final AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional(rollbackOn = AlunoMesmoCPFException.class)
    public void cadastrarOuAtualizarAluno(final Aluno aluno) throws AlunoMesmoCPFException {
        try {
            List<Aluno> alunos = this.alunoRepository.findAll();

            for(Aluno a : alunos) {
                if(a.getCpf().equals(aluno.getCpf())) {
                    throw new AlunoMesmoCPFException(a.getCpf());
                }
            }

            this.alunoRepository.save(aluno);
        } catch (DataIntegrityViolationException e) {
            Throwable rootCause = e.getRootCause();

            if(rootCause instanceof SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {

                if(sqlIntegrityConstraintViolationException.getErrorCode()==1062 && sqlIntegrityConstraintViolationException.getMessage().contains("next_aluno.uk_cpf")) {
                    throw new AlunoMesmoCPFException(aluno.getCpf());
                } else {
                    throw e;
                }
            } else {
                throw e;
            }
        }
    }

    @Transactional
    public Set<Aluno> listagemAlunos() {
        return new LinkedHashSet<>(this.alunoRepository.findAll());
    }

    @Transactional
    public Aluno obterAluno(final Long id) {
        return this.alunoRepository.findById(id).orElse(null);
    }
}
