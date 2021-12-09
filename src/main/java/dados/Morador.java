package dados;

import javax.persistence.Entity;

@Entity
public class Morador extends Pessoa {
    public Morador() {}

    public Morador(Integer id, String nome, String telefone, String email, String cpf) {
        super(nome, telefone, email, cpf);
    }
}
