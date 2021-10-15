package school.cesar.next.project.springboot.model.interfaces;

import java.io.Serializable;

public interface NextEntity extends Serializable {

    Long getId();

    void setId(final Long id);

    String getNome();

    void setNome(final String nome);

}
