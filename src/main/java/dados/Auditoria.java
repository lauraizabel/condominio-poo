package dados;

import enums.TipoAuditoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue
    private Integer idAuditoria;
    private String nomeTabela;
    private Date dataAlteracao;
    private String camposAlterados;
    private TipoAuditoria tipoAuditoria;

    public Auditoria() {
    }

    public Auditoria(Date dataAlteracao, String camposAlterados, TipoAuditoria tipoAuditoria, String nomeTabela) {
        this.dataAlteracao = dataAlteracao;
        this.camposAlterados = camposAlterados;
        this.tipoAuditoria = tipoAuditoria;
        this.nomeTabela = nomeTabela;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getCamposAlterados() {
        return camposAlterados;
    }

    public void setCamposAlterados(String camposAlterados) {
        this.camposAlterados = camposAlterados;
    }

    public TipoAuditoria getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(TipoAuditoria tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }
}
