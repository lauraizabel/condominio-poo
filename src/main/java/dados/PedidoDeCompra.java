package dados;

import javax.persistence.*;

@Entity
public class PedidoDeCompra {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY)
    private Produto produto;

    public PedidoDeCompra() {}

    public PedidoDeCompra(Produto produto) {
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
