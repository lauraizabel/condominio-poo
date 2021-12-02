package dados;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity=Funcionario.class, fetch= FetchType.EAGER)
    private Funcionario requerente;
    @OneToMany(targetEntity=Produto.class, fetch= FetchType.EAGER)
    private List<Produto> produtos;

    public Pedido() {}
    
    public Pedido(Integer id, Funcionario requerente, ArrayList<Produto> produtos) {
        this.id = id;
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