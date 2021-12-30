package dados;

import javax.persistence.*;

@Entity
public class AcessoPermitido extends Pessoa {
    @ManyToOne(targetEntity = Apartamento.class, fetch = FetchType.LAZY)
    private Apartamento apartamento;
    private Boolean permitido;

    public AcessoPermitido() {
    }

    public AcessoPermitido(String nome, String cpf, Apartamento apartamento, String telefone, String email,
                           Boolean permitido) {
        super(nome, telefone, email, cpf);
        this.apartamento = apartamento;
        this.permitido = permitido;
    }

    public Apartamento getApartamento() {
        return this.apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public Boolean isPermitido() {
        return this.permitido;
    }

    public void setPermitido(Boolean permitido) {
        this.permitido = permitido;
    }

}
