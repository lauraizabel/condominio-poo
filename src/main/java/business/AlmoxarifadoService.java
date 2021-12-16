package business;

import DAO.AlmoxarifadoDAO;

import dados.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlmoxarifadoService implements IService<Almoxarifado> {
    private AlmoxarifadoDAO almoxarifadoDAO = new AlmoxarifadoDAO();
    private ProdutoService produtoService = new ProdutoService();
    private PedidoDeCompraService pedidoDeCompraService = new PedidoDeCompraService();

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

    // testado
    public ArrayList<Almoxarifado> findByProductId(Integer productId) {
        ArrayList<Almoxarifado> almoxarifados = almoxarifadoDAO.findByProductId(productId);

        if (almoxarifados.isEmpty()) {
            return null;
        }

        return almoxarifados;
    }

    // testado
    public Produto getProdutoById(Integer id) {
        return produtoService.getById(id);
    }

    // testado
    public boolean adicionarProduto(Produto produto, Funcionario funcionario, Integer quantidade) {
        // cadastrar novo registro no almoxarifado
        Date dataRegistro = new Date();
        Almoxarifado almoxarifado = new Almoxarifado(produto, funcionario, quantidade, 0, dataRegistro);
        return save(almoxarifado);
    }

    // testado
    public boolean removerProduto(Produto produto, Funcionario funcionario, Integer quantidade) {
        if ( produto.getQuantidade() >= quantidade ) {
            // removendo quantidade do estoque
            produto.setQuantidade(produto.getQuantidade() - quantidade);
            boolean updated = produtoService.update(produto) != null;
            if ( updated ) {
                // checar ponto de pedido
                if ( this.checkPontoDePedido(produto) ) {
                    this.fazerPedidoDeCompra(produto);
                    System.out.println("Ponto de pedido atingido");
                }
                // adicionar registro de remoção de produto no almoxarifado
                Date dataRegistro = new Date();
                Almoxarifado almoxarifado = new Almoxarifado(produto, funcionario, 0, quantidade, dataRegistro);
                return save(almoxarifado);
            }
            return false;
        }
        return false;
    }

    // testado
    public int getQuatidadeEmEstoque(Integer id) {
        return produtoService.getById(id).getQuantidade();
    }

    // testado
    public Funcionario getFuncionario(Integer id) {
        Almoxarifado almoxarifado = almoxarifadoDAO.getById(id);
        return almoxarifado.getFuncionario();
    }

    // testado
    public boolean checkPontoDePedido(Produto produto) {
        return produto.getQuantidade() <= produto.getPontoDePedido();
    }

    // testado
    public boolean fazerPedidoDeCompra(Produto produto) {
        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra(produto);
        PedidoDeCompra pedidosDeCompra = pedidoDeCompraService.getByProductId(produto.getId());
        if ( pedidosDeCompra != null ) {
            return pedidoDeCompraService.save(pedidoDeCompra);
        }
        return false;
    }
}
