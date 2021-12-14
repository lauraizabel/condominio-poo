package business;

import DAO.AlmoxarifadoDAO;
import DAO.CompraDAO;
import dados.Compra;
import dados.Produto;

import java.util.ArrayList;

public class CompraService implements IService<Compra> {
    private CompraDAO compraDAO = new CompraDAO();
    private AlmoxarifadoService almoxarifadoService = new AlmoxarifadoService();
    private ProdutoService produtoService = new ProdutoService();

    @Override
    public Compra getById(Integer id) {
        return compraDAO.getById(id);
    }

    @Override
    public ArrayList<Compra> getAll() {
        return compraDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return compraDAO.deleteById(id);
    }

    @Override
    public boolean save(Compra compra) {
        boolean saved = compraDAO.save(compra);
        Produto produto = compra.getProduto();
        produto.setQuantidade(produto.getQuantidade() + compra.getQuantidade());

        boolean produtoSalvo = produtoService.save(produto);
        almoxarifadoService.adicionarProduto(produto, compra.getFuncionario());

        return saved && produtoSalvo;
    }

    @Override
    public Compra update(Compra compra) {
        return compraDAO.update(compra);
    }

}
