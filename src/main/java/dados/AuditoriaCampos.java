package dados;

import enums.TipoAuditoria;

import java.util.Date;

public class AuditoriaCampos {
    private Date dataAlteracao;
    private TipoAuditoria tipoAuditoria;

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public TipoAuditoria getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(TipoAuditoria tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }
}
