package dados;

import java.util.Date;

public class Auditoria {
    private String tabelaAlterada;
    private String idAlteracao;
    private String idFuncionario;
    private Date dataAlteracao;

    public Auditoria(String tabelaAlterada, String idAlteracao, String idFuncionario, Date dataAlteracao) {
        this.tabelaAlterada = tabelaAlterada;
        this.idAlteracao = idAlteracao;
        this.idFuncionario = idFuncionario;
        this.dataAlteracao = dataAlteracao;
    }

    public String getTabelaAlterada() {
        return tabelaAlterada;
    }

    public void setTabelaAlterada(String tabelaAlterada) {
        this.tabelaAlterada = tabelaAlterada;
    }

    public String getIdAlteracao() {
        return this.idAlteracao;
    }

    public String getIdFuncionario() {
        return this.idFuncionario;
    }

    public Date getDataAlteracao() {
        return this.dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
