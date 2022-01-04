package tables;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CompraTable {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty quantidade;
    private final SimpleStringProperty produto;
    private final SimpleStringProperty funcionario;
    private final SimpleDoubleProperty ValorUnitario;
    private final SimpleStringProperty dataAlteracao;


    public CompraTable(
            Integer id,
            Integer quantidade,
            Double valorUnitario,
            String produto,
            String funcionario,
            String dataAlteracao
    ) {
        this.produto = new SimpleStringProperty(produto);
        this.funcionario = new SimpleStringProperty(funcionario);
        this.id = new SimpleIntegerProperty(id);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.ValorUnitario = new SimpleDoubleProperty(valorUnitario);
        this.dataAlteracao = new SimpleStringProperty(dataAlteracao);
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

    public int getQuantidade() {
        return quantidade.get();
    }

    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
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

    public double getValorUnitario() {
        return ValorUnitario.get();
    }

    public SimpleDoubleProperty valorUnitarioProperty() {
        return ValorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.ValorUnitario.set(valorUnitario);
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
}
