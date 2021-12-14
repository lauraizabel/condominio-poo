package business;

import DAO.AlmoxarifadoDAO;
import DAO.ApartamentoDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import dados.Almoxarifado;
import dados.Apartamento;
import dados.Compra;
import dados.Funcionario;
import dados.Produto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlmoxarifadoService implements IService<Almoxarifado> {
    private AlmoxarifadoDAO almoxarifadoDAO = new AlmoxarifadoDAO();
    private ProdutoService produtoService = new ProdutoService();
    private CompraService compraService = new CompraService();

    @Override
    public Almoxarifado getById(Integer id) {
        return almoxarifadoDAO.getById(id);
    }

    @Override
    public ArrayList<Almoxarifado> getAll() {
        return almoxarifadoDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return almoxarifadoDAO.deleteById(id);
    }

    @Override
    public boolean save(Almoxarifado almoxarifado) {
        return almoxarifadoDAO.save(almoxarifado);
    }

    @Override
    public Almoxarifado update(Almoxarifado almoxarifado) {
        return almoxarifadoDAO.update(almoxarifado);
    }

    public Almoxarifado findByProductId(Integer productId) {
        List<Almoxarifado> almoxarifados = almoxarifadoDAO.findByProductId(productId);

        if (almoxarifados.isEmpty()) {
            return null;
        }

        return almoxarifados.get(0);
    }
    
    public List<Almoxarifado> getAllAlmoxarifadosByProduct(Integer productId) {
        List<Almoxarifado> almoxarifados = almoxarifadoDAO.findByProductId(productId);

        if (almoxarifados.isEmpty()) {
            return null;
        }

        return almoxarifados;
    }

    public Produto getProdutoById(Integer id) {
        return produtoService.getById(id);
    }

    public boolean adicionarProduto(Produto produto, Funcionario funcionario) {
        Almoxarifado findedAlmoxarifado = findByProductId(produto.getId());

        if (findedAlmoxarifado == null) {
            // cadastrar novo almoxarifado
            Almoxarifado almoxarifado = new Almoxarifado();
            almoxarifado.setProduto(produto);
            almoxarifado.setFuncionario(funcionario);
            return save(almoxarifado);
        }

        Produto prodUpdated = produtoService.getById(produto.getId());
        findedAlmoxarifado.setProduto(prodUpdated);

        return almoxarifadoDAO.update(findedAlmoxarifado) != null;
    }

    public boolean removerDoEstoque(Integer almoxarifadoId, Integer quantidade) {
        Almoxarifado almoxarifado = almoxarifadoDAO.getById(almoxarifadoId);
        almoxarifado.setQuantidadeRemovida(quantidade);
        almoxarifadoDAO.update(almoxarifado);
        Produto novoProduto = almoxarifado.getProduto();
        novoProduto.setQuantidade(novoProduto.getQuantidade() - quantidade);

        return produtoService.update(novoProduto) != null;
    }

    public int getQuatidadeEmEstoque(Integer id) {
        Almoxarifado almoxarifado = almoxarifadoDAO.getById(id);
        return almoxarifado.getProduto().getQuantidade();
    }

    public Funcionario getFuncionario(Integer id) {
        Almoxarifado almoxarifado = almoxarifadoDAO.getById(id);
        return almoxarifado.getFuncionario();
    }

    public boolean checkPontoDePedido(Produto produto) {
        return produto.getQuantidade() <= produto.getPontoDePedido();
    }

    // -- pedidos de compra
    public void fazerPedidoDeCompra(Almoxarifado almoxarifado, int quantidade, double valorUnitario) {
        if (checkPontoDePedido(almoxarifado.getProduto())) {
            Compra novaCompra = new Compra(almoxarifado.getProduto(), quantidade, new Date(),
                    almoxarifado.getFuncionario(), valorUnitario);
            // Aqui eu espero que ele salve e entre no fluxo da função de adicionar produto
            // daqui
            compraService.save(novaCompra);
        }
    }
}
