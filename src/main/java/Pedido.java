import java.util.ArrayList;

public class Pedido {
    private String id;
    private Funcionario requerente;
    private ArrayList<Produto> produtos;
    
    public Pedido(String id, Funcionario requerente, ArrayList<Produto> produtos) {
        this.id = id;
        this.requerente = requerente;
        this.produtos = produtos;
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<Produto> getProduto() {
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