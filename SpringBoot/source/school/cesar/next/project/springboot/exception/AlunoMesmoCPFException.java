package school.cesar.next.project.springboot.exception;

import school.cesar.next.project.springboot.exception.abstracts.NextException;

public class AlunoMesmoCPFException extends NextException {
    public AlunoMesmoCPFException(final String cpf) {
        super("Dois alunos possuem o CPF: " + cpf + ".");
    }
}
