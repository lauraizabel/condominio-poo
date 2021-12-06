package dados;

import java.util.ArrayList;

public class Almoxarifado {
    private ArrayList<Produto> produtos = new ArrayList<Produto>();

    public Almoxarifado(){}
   
    public Almoxarifado(ArrayList<Produto> produtos){
        this.produtos = produtos;
    }

    public ArrayList<Produto> getProdutos() {
        return this.produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

}
