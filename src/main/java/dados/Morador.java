package dados;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Audited
public class Morador extends Pessoa implements Serializable {
    public Morador() {}

    public Morador(String nome, String telefone, String email, String cpf) {
        super(nome, telefone, email, cpf);
    }
}
