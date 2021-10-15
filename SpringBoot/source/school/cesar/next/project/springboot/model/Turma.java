package school.cesar.next.project.springboot.model;

import school.cesar.next.project.springboot.model.abstracts.AbstractNextEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "next_schema", name = "next_turma")
public class Turma extends AbstractNextEntity {

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "id_escola", referencedColumnName = "id", nullable = false)
    private Escola escola;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "id_professor", referencedColumnName = "id", nullable = false)
    private Professor professor;

    @ManyToMany(targetEntity = Aluno.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(schema = "next_schema", name = "next_aluno_turma",
            joinColumns = @JoinColumn(name = "id_turma", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_aluno", referencedColumnName = "id", nullable = false)
    )
    private Set<Aluno> alunos;

    public Turma() {
        this(null);
    }

    public Turma(final String nome) {
        super(nome);
        this.alunos = new HashSet<>();
    }

    public Escola getEscola() {
        return this.escola;
    }

    public void setEscola(final Escola escola) {
        this.escola = escola;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    public void setProfessor(final Professor professor) {
        this.professor = professor;
    }

    public Set<Aluno> getAlunos() {
        return this.alunos;
    }

    public void setAlunos(final Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return String.format("{%s id=%d, nome='%s', escola=%s, professor=%s}", super.toString(), super.getId(), super.getNome(), this.getEscola().toString(), this.getProfessor().toString());
    }

}
