package school.cesar.next.project.springboot.model;

import school.cesar.next.project.springboot.model.abstracts.AbstractNextEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_schema", name = "next_escola")
public class Escola extends AbstractNextEntity {

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "id_tipo_escola", referencedColumnName = "id", nullable = false)
    private TipoEscola tipo;

    @OneToMany(targetEntity = Turma.class, mappedBy = "escola", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Turma> turmas;

    public Escola() {
        this(null);
    }

    public Escola(final String nome) {
        super(nome);
        this.turmas = new ArrayList<>();
    }

    public TipoEscola getTipo() {
        return this.tipo;
    }

    public void setTipo(final TipoEscola tipo) {
        this.tipo = tipo;
    }

    public List<Turma> getTurmas() {
        return this.turmas;
    }

    public void setTurmas(final List<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public String toString() {
        return String.format("{%s id=%d, nome='%s', tipo=%s}", super.toString(), super.getId(), super.getNome(), this.getTipo().toString());
    }

}
