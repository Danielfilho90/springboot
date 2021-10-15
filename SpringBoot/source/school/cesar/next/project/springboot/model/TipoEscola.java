package school.cesar.next.project.springboot.model;

import school.cesar.next.project.springboot.model.abstracts.AbstractNextEntity;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "next_schema", name = "next_tipo_escola")
public class TipoEscola extends AbstractNextEntity {

    @OneToMany(targetEntity = Escola.class, mappedBy = "tipo", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Escola> escolas;

    public TipoEscola() {
        this(null);
    }

    public TipoEscola(final String nome) {
        super(nome);
        this.escolas = new ArrayList<>();
    }

    public List<Escola> getEscolas() {
        return this.escolas;
    }

    public void setEscolas(final List<Escola> escolas) {
        this.escolas = escolas;
    }

    @Override
    public String toString() {
        return String.format("{%s id=%d, nome='%s'}", super.toString(), super.getId(), super.getNome());
    }

}
