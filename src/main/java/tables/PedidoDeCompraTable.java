package tables;

import dados.Produto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class PedidoDeCompraTable {
    private final SimpleStringProperty produto;
    private final SimpleIntegerProperty id;

    public PedidoDeCompraTable(
            String produto,
            Integer id
    ) {
        this.produto = new SimpleStringProperty(produto);
        this.id = new SimpleIntegerProperty(id);
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

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
