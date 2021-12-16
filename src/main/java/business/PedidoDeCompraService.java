package business;

import DAO.PedidoDeCompraDAO;
import dados.PedidoDeCompra;

import java.util.ArrayList;

public class PedidoDeCompraService implements IService<PedidoDeCompra>{
    private PedidoDeCompraDAO PedidoDeCompraDAO = new PedidoDeCompraDAO();

    @Override
    public PedidoDeCompra getById(Integer id) {
        return PedidoDeCompraDAO.getById(id);
    }

    @Override
    public ArrayList<PedidoDeCompra> getAll() {
        return PedidoDeCompraDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return PedidoDeCompraDAO.deleteById(id);
    }

    @Override
    public boolean save(PedidoDeCompra pedidoDeCompra) {
        return PedidoDeCompraDAO.save(pedidoDeCompra);
    }

    @Override
    public PedidoDeCompra update(PedidoDeCompra pedidoDeCompra) {
        return PedidoDeCompraDAO.update(pedidoDeCompra);
    }

    public PedidoDeCompra getByProductId(Integer produtoId) {
        return PedidoDeCompraDAO.getByProductId(produtoId);
    }
}
