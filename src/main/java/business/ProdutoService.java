package business;

import DAO.ProdutoDAO;
import dados.Produto;

import java.util.ArrayList;

public class ProdutoService implements IService<Produto> {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    @Override
    public Produto getById(Integer id) {
        Produto produto = produtoDAO.getById(id);
        return produto;
    }

    @Override
    public ArrayList<Produto> getAll() {
        ArrayList<Produto> produto = produtoDAO.getAll();
        return produto;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = produtoDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Produto produto) {
        boolean result = produtoDAO.save(produto);
        return result;
    }

    @Override
    public Produto update(Produto produto) {
        Produto produtoUpdated = produtoDAO.update(produto);
        return produtoUpdated;
    }
}
