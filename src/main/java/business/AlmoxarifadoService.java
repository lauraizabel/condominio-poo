package business;

import DAO.ProdutoDAO;
import dados.Produto;

import java.util.ArrayList;

public class AlmoxarifadoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public Produto getpProdutoById(Integer id) {
        return produtoDAO.getById(id);
    }

    public ArrayList<Produto> getAllProdutos() {
        return produtoDAO.getAll();
    }

    public Produto updateProduto(Produto produto) {
        return produtoDAO.update(produto);
    }

    // -- adicionar produto (implementada com o save do produto)
    public boolean adicionarProduto(Produto produto) {
        return produtoDAO.save(produto);
    }

    // -- baixa de estoque (implementada com o deleteById do produto)
    public boolean removerDoEstoque(Integer id) {
        return produtoDAO.deleteById(id);
    }

    // -- quantidade de um produto em estoque
    public int getQuatidadeEmEstoque(String productName) {
        return produtoDAO.getByName(productName).size();
    }

    // -- quem pegou o produto do almoxarifado
    //  NAO IMPLEMENTADO -> PRECISA DE NOVA TABELA NO BD

    // -- alerta do ponto de pedido
    public boolean checkPontoDePedido() {
        //  NAO IMPLEMENTADO -> PRECISA DE NOVA TABELA NO BD
        return false;
    }

    // -- pedidos de compra
    public void fazerPedidoDeCompra(Produto produto, int quantidade) {
        //  NAO IMPLEMENTADO
    }
}
