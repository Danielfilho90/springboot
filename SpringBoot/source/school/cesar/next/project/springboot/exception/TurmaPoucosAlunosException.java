package school.cesar.next.project.springboot.exception;

import school.cesar.next.project.springboot.exception.abstracts.NextException;

public class TurmaPoucosAlunosException extends NextException {

    public TurmaPoucosAlunosException(final int quantidadeAlunos) {
        super("Turma não pode ter menos de 3 alunos! A turma atual tem " + quantidadeAlunos + " alunos!");
    }

}
