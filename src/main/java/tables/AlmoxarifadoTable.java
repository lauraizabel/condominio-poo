package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlmoxarifadoTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty produto;
    private final SimpleStringProperty funcionario;
    private final SimpleIntegerProperty quantidadeAdicionada;
    private final SimpleIntegerProperty quantidadeRemovida;
    private final SimpleObjectProperty dataAlteracao;


    public AlmoxarifadoTable(
            Integer id,
            Integer quantidadeAdicionada,
            Integer quantidadeRemovida,
            String produto,
            String funcionario,
            Date dataAlteracao
    ) {
        this.produto = new SimpleStringProperty(produto);
        this.funcionario = new SimpleStringProperty(funcionario);
        this.id = new SimpleIntegerProperty(id);
        this.quantidadeAdicionada = new SimpleIntegerProperty(quantidadeAdicionada);
        this.quantidadeRemovida = new SimpleIntegerProperty(quantidadeRemovida);
        this.dataAlteracao = new SimpleObjectProperty(dataAlteracao);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getProduto() {
        return produto.get();
    }

    public SimpleStringProperty produtoProperty() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto.set(produto);
    }

    public String getFuncionario() {
        return funcionario.get();
    }

    public SimpleStringProperty funcionarioProperty() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario.set(funcionario);
    }

    public int getQuantidadeAdicionada() {
        return quantidadeAdicionada.get();
    }

    public SimpleIntegerProperty quantidadeAdicionadaProperty() {
        return quantidadeAdicionada;
    }

    public void setQuantidadeAdicionada(int quantidadeAdicionada) {
        this.quantidadeAdicionada.set(quantidadeAdicionada);
    }

    public int getQuantidadeRemovida() {
        return quantidadeRemovida.get();
    }

    public SimpleIntegerProperty quantidadeRemovidaProperty() {
        return quantidadeRemovida;
    }

    public void setQuantidadeRemovida(int quantidadeRemovida) {
        this.quantidadeRemovida.set(quantidadeRemovida);
    }

    public SimpleObjectProperty getDataAlteracao() {
        return dataAlteracao;
    }
}
