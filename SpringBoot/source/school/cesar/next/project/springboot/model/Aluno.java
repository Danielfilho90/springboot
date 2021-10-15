package school.cesar.next.project.springboot.model;

import school.cesar.next.project.springboot.model.abstracts.AbstractNextEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "next_schema", name = "next_aluno", uniqueConstraints =  { @UniqueConstraint(name = "uk_aluno", columnNames = "cpf") })
public class Aluno extends AbstractNextEntity {

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @ManyToMany(mappedBy = "alunos", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private Set<Turma> turmas;

    public Aluno() {
        this(null);
    }

    public Aluno(final String nome) {
        super(nome);
        this.turmas = new HashSet<>();
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

    public Set<Turma> getTurmas() {
        return this.turmas;
    }

    public void setTurmas(final Set<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return String.format("{%s id=%d, nome='%s', cpf='%s'}", super.toString(), super.getId(), super.getNome(), this.getCpf());
    }

}
