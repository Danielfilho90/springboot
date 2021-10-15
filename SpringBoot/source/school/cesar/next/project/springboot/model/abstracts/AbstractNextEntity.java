package school.cesar.next.project.springboot.model.abstracts;

import school.cesar.next.project.springboot.model.interfaces.NextEntity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractNextEntity implements NextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    protected AbstractNextEntity() {
        super();
    }

    protected AbstractNextEntity(final String nome) {
        super();
        this.nome = nome;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public void setNome(final String nome){
        this.nome = nome;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (!(this.getClass().equals(object.getClass()))) {
            return false;
        }

        return Objects.equals(this.getId(), ((AbstractNextEntity) object).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

}
