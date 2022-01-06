package tables;

import javafx.beans.property.SimpleStringProperty;

public class AuditoriaTable {
    private final SimpleStringProperty dataAlteracao;
    private final SimpleStringProperty tipoAlteracao;

    public AuditoriaTable(String dataAlteracao, String tipoAlteracao) {
        this.dataAlteracao = new SimpleStringProperty(dataAlteracao);
        this.tipoAlteracao = new SimpleStringProperty(tipoAlteracao);;
    }

    public String getDataAlteracao() {
        return dataAlteracao.get();
    }

    public SimpleStringProperty dataAlteracaoProperty() {
        return dataAlteracao;
    }

    public void setDataAlteracao(String dataAlteracao) {
        this.dataAlteracao.set(dataAlteracao);
    }

    public String getTipoAlteracao() {
        return tipoAlteracao.get();
    }

    public SimpleStringProperty tipoAlteracaoProperty() {
        return tipoAlteracao;
    }

    public void setTipoAlteracao(String tipoAlteracao) {
        this.tipoAlteracao.set(tipoAlteracao);
    }
}
