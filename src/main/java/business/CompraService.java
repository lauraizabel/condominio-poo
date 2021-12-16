package business;

import DAO.CompraDAO;
import dados.Compra;
import dados.PedidoDeCompra;
import dados.Produto;

import java.util.ArrayList;
import java.util.Date;

public class CompraService implements IService<Compra> {
    private CompraDAO compraDAO = new CompraDAO();
    private AlmoxarifadoService almoxarifadoService = new AlmoxarifadoService();
    private ProdutoService produtoService = new ProdutoService();
    private PedidoDeCompraService pedidoDeCompraService = new PedidoDeCompraService();

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
        if ( saved ) {
            Produto produto = compra.getProduto();
            // atualizando quantidade do produto no BD
            produto.setQuantidade(produto.getQuantidade() + compra.getQuantidade());
            Produto produtoAtualizado = produtoService.update(produto);
            boolean registrado = almoxarifadoService.adicionarProduto(produto, compra.getFuncionario(), compra.getQuantidade());
            if ( !almoxarifadoService.checkPontoDePedido(produtoAtualizado) ) {
                PedidoDeCompra pedidoDeCompra = pedidoDeCompraService.getByProductId(produto.getId());
                pedidoDeCompraService.deleteById(pedidoDeCompra.getId());
            }
            return ( produtoAtualizado != null && registrado);
        }
        return false;
    }



    @Override
    public Compra update(Compra compra) {
        return compraDAO.update(compra);
    }

    public Double valorMedioProduto (Integer productId, Date minDate, Date maxDate) {
        Double valorMedio = 0.0;
        Integer qtdTotalDeProdutos = 0;

        ArrayList<Compra> compras = compraDAO.findByProductId(productId);

        if (compras.size() == 0) {
            return valorMedio;
        }

        for (Compra compra: compras) {
            // se estiver no intervalo (inclusivo) de datas
            if ( !minDate.after(compra.getDataAlteracao()) && !maxDate.before(compra.getDataAlteracao()) ) {
                valorMedio += compra.getValorUnitario() * compra.getQuantidade();
                qtdTotalDeProdutos += compra.getQuantidade();
            }
        }

        valorMedio = valorMedio / qtdTotalDeProdutos;
        return valorMedio;
    }
}
