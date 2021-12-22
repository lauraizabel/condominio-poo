package dados;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class AcessoPermitido extends Pessoa {
    private Integer idAcesso;
    @ManyToOne(targetEntity = Apartamento.class, fetch = FetchType.LAZY)
    private Apartamento apartamento;
    private Boolean permitido;

    public AcessoPermitido() {
    }

    public AcessoPermitido(String nome, String cpf, Apartamento apartamento, String telefone, String email,
            Morador permissor, Boolean permitido, Integer idAcesso) {
        super(nome, telefone, email, cpf);
        this.apartamento = apartamento;
        this.permitido = permitido;
        this.idAcesso = idAcesso;
    }

    public Integer getIdAcesso() {
        return this.idAcesso;
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
