package dados;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Auditoria {
    @Id @GeneratedValue
    private Integer idAlteracao;
    private String tabelaAlterada;
    private Integer idFuncionario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    public Auditoria() {}

    public Auditoria(String tabelaAlterada, Integer idFuncionario, Date dataAlteracao) {
        this.tabelaAlterada = tabelaAlterada;
        this.idFuncionario = idFuncionario;
        this.dataAlteracao = dataAlteracao;
    }

    public String getTabelaAlterada() {
        return tabelaAlterada;
    }

    public void setTabelaAlterada(String tabelaAlterada) {
        this.tabelaAlterada = tabelaAlterada;
    }

    public Integer getIdAlteracao() {
        return this.idAlteracao;
    }

    public Integer getIdFuncionario() {
        return this.idFuncionario;
    }

    public Date getDataAlteracao() {
        return this.dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
