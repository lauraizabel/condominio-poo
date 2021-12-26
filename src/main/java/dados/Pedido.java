package dados;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
public class Pedido {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY)
    private Funcionario requerente;
    @ManyToMany(targetEntity = Produto.class, fetch = FetchType.LAZY)
    private List<Produto> produtos;

    public Pedido() {
    }

    public Pedido(Funcionario requerente, ArrayList<Produto> produtos) {
        this.requerente = requerente;
        this.produtos = produtos;
    }

    public Integer getId() {
        return this.id;
    }

    public List<Produto> getProduto() {
        return this.produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public Funcionario getRequerente() {
        return this.requerente;
    }

    public void setRequerente(Funcionario requerente) {
        this.requerente = requerente;
    }
}