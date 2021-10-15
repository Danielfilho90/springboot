package school.cesar.next.project.springboot.model;

import school.cesar.next.project.springboot.model.abstracts.AbstractNextEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_schema", name = "next_professor")
public class Professor extends AbstractNextEntity {

    @OneToMany(targetEntity = Turma.class, mappedBy = "professor", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<Turma> turmas;

    public Professor() {
        this(null);
    }

    public Professor(final String nome) {
        super(nome);
        this.turmas = new ArrayList<>();
    }

    public List<Turma> getTurmas() {
        return this.turmas;
    }

    public void setTurmas(final List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return String.format("{%s id=%d, nome='%s'}", super.toString(), super.getId(), super.getNome());
    }

}
