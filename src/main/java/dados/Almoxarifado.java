package dados;

import java.util.ArrayList;

public class Almoxarifado {
    private String id;
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private ArrayList<Servico> servicos = new ArrayList<Servico>();

    public String getId() {
        return this.id;
    }

    public Almoxarifado(ArrayList<Produto> produtos){
        this.produtos = produtos;
    }

    public ArrayList<Produto> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<Servico> getServicos() {
        return this.servicos;
    }

    public void setServicos(ArrayList<Servico> servicos) {
        this.servicos = servicos;
    }
}
