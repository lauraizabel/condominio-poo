package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PedidoTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty funcionario;
    private final SimpleStringProperty produtos;


    public PedidoTable(
            Integer id,
            String funcionario,
            String produtos
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.funcionario = new SimpleStringProperty(funcionario);
        this.produtos = new SimpleStringProperty(produtos);
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

    public String getFuncionario() {
        return funcionario.get();
    }

    public SimpleStringProperty funcionarioProperty() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario.set(funcionario);
    }

    public String getProdutos() {
        return produtos.get();
    }

    public SimpleStringProperty produtosProperty() {
        return produtos;
    }

    public void setProdutos(String produtos) {
        this.produtos.set(produtos);
    }
}
