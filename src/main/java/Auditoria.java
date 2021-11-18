import java.util.Date;

public class Auditoria {
    private String tabelaAlterada;
    private Number idAlteracao;
    private Number idFuncionario;
    private Date dataAlteracao;

    public String getTabelaAlterada() {
        return tabelaAlterada;
    }

    public void setTabelaAlterada(String tabelaAlterada) {
        this.tabelaAlterada = tabelaAlterada;
    }

    public Number getIdAlteracao() {
        return idAlteracao;
    }

    public Number getIdFuncionario() {
        return idFuncionario;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
