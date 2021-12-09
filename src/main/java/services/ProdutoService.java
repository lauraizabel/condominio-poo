package services;

import DAO.ProdutoDAO;
import dados.Fornecedor;
import dados.Produto;
import repositories.IRepository;

import java.util.List;

public class ProdutoService implements IRepository<Produto> {
    private ProdutoDAO produtoDAO;

    @Override
    public Produto getById(Integer id) {
        Produto produto = produtoDAO.getById(id);
        return produto;
    }

    @Override
    public List<Produto> getAll() {
        List<Produto> produto = produtoDAO.getAll();
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
