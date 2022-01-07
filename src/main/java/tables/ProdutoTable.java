package tables;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProdutoTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;
    private final SimpleIntegerProperty quantidade;
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty fornecedor;
    private final SimpleIntegerProperty pontoDePedido;
    private final SimpleDoubleProperty precoMedio;

    public ProdutoTable(
            Integer id,
            String nome,
            Integer quantidade,
            String fornecedor,
            String codigo,
            Integer pontoDePedido,
            Double precoMedio
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.codigo = new SimpleStringProperty(codigo);
        this.fornecedor = new SimpleStringProperty(fornecedor);
        this.pontoDePedido =  new SimpleIntegerProperty(pontoDePedido);
        this.precoMedio =  new SimpleDoubleProperty(precoMedio);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public int getQuantidade() {
        return quantidade.get();
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
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

    public String getFornecedor() {
        return fornecedor.get();
    }

    public SimpleStringProperty fornecedorProperty() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor.set(fornecedor);
    }

    public int getPontoDePedido() {
        return pontoDePedido.get();
    }

    public SimpleIntegerProperty pontoDePedidoProperty() {
        return pontoDePedido;
    }

    public void setPontoDePedido(int pontoDePedido) {
        this.pontoDePedido.set(pontoDePedido);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public double getPrecoMedio() {
        return precoMedio.get();
    }

    public SimpleDoubleProperty precoMedioProperty() {
        return precoMedio;
    }

    public void setPrecoMedio(double precoMedio) {
        this.precoMedio.set(precoMedio);
    }
}
