package school.cesar.next.project.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.cesar.next.project.springboot.exception.AlunoMesmoCPFException;
import school.cesar.next.project.springboot.exception.TurmaPoucosAlunosException;
import school.cesar.next.project.springboot.model.Aluno;
import school.cesar.next.project.springboot.model.Turma;
import school.cesar.next.project.springboot.repository.TurmaRepository;
import javax.transaction.Transactional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    private final AlunoService alunoService;

    @Autowired
    public TurmaService(final TurmaRepository turmaRepository, final AlunoService alunoService) {
        this.turmaRepository = turmaRepository;
        this.alunoService = alunoService;
    }

    @Transactional
    public void cadastraTurma(Turma turma) throws TurmaPoucosAlunosException, AlunoMesmoCPFException {
        if(turma.getAlunos().size()<3) {
            throw new TurmaPoucosAlunosException(turma.getAlunos().size());
        }

        for(Aluno aluno : turma.getAlunos()) {
            this.alunoService.cadastrarOuAtualizarAluno(aluno);
        }

        this.turmaRepository.save(turma);
    }
}
