package business;

import DAO.PedidoDeCompraDAO;
import dados.Auditoria;
import dados.Espaco;
import dados.PedidoDeCompra;

import java.util.ArrayList;

public class PedidoDeCompraService implements IService<PedidoDeCompra>{
    private PedidoDeCompraDAO pedidoDeCompraDAO = new PedidoDeCompraDAO();

    @Override
    public PedidoDeCompra getById(Integer id) {
        return pedidoDeCompraDAO.getById(id);
    }

    @Override
    public ArrayList<PedidoDeCompra> getAll() {
        return pedidoDeCompraDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return pedidoDeCompraDAO.deleteById(id);
    }

    @Override
    public boolean save(PedidoDeCompra pedidoDeCompra) {
        return pedidoDeCompraDAO.save(pedidoDeCompra);
    }

    @Override
    public PedidoDeCompra update(PedidoDeCompra pedidoDeCompra) {
        return pedidoDeCompraDAO.update(pedidoDeCompra);
    }

    public PedidoDeCompra getByProductId(Integer produtoId) {
        return pedidoDeCompraDAO.getByProductId(produtoId);
    }

    @Override
    public ArrayList<Auditoria> getAllAuditory() {
        return pedidoDeCompraDAO.getAllAuditory();
    }
}
