package dados;

import enums.TipoAuditoria;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class AcessoPermitidoAuditoria extends Pessoa {
    @ManyToOne(targetEntity = Apartamento.class, fetch = FetchType.EAGER)
    private Apartamento apartamento;
    private Boolean permitido;
    private Date dataAlteracao;
    private TipoAuditoria tipoAlteracao;

    public AcessoPermitidoAuditoria() {
    }

    public AcessoPermitidoAuditoria(String nome, String cpf, Apartamento apartamento, String telefone, String email,
                                    Boolean permitido, TipoAuditoria tipoAuditoria, Date dataAlteracao) {
        super(nome, telefone, email, cpf);
        this.apartamento = apartamento;
        this.permitido = permitido;
        this.dataAlteracao = dataAlteracao;
        this.tipoAlteracao = tipoAuditoria;
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

    public Boolean getPermitido() {
        return permitido;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public TipoAuditoria getTipoAlteracao() {
        return tipoAlteracao;
    }

    public void setTipoAlteracao(TipoAuditoria tipoAlteracao) {
        this.tipoAlteracao = tipoAlteracao;
    }
}
