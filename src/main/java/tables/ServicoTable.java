package tables;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ServicoTable {
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty descricao;
    private final SimpleDoubleProperty valor;
    private final SimpleStringProperty fornecedor;
    private final SimpleStringProperty requerente;


    public ServicoTable(
            String codigo,
            String descricao,
            double valor,
            String fornecedor,
            String requerente
    ) {
        this.codigo = new SimpleStringProperty(codigo);
        this.descricao = new SimpleStringProperty(descricao);
        this.valor = new SimpleDoubleProperty(valor);
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.requerente = new SimpleStringProperty(requerente);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public SimpleStringProperty codigoProperty() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public SimpleStringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public double getValor() {
        return valor.get();
    }

    public SimpleDoubleProperty valorProperty() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor.set(valor);
    }

    public String getFornecedor() {
        return fornecedor.get();
    }

    public SimpleStringProperty fornecedorProperty() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor.set(fornecedor);
    }

    public String getRequerente() {
        return requerente.get();
    }

    public SimpleStringProperty requerenteProperty() {
        return requerente;
    }

    public void setRequerente(String requerente) {
        this.requerente.set(requerente);
    }
}
