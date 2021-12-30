package dados;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
public class PedidoDeCompra extends CustomAuditory<PedidoDeCompra> {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = Produto.class, fetch = FetchType.LAZY)
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
