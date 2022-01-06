package business;

import DAO.PedidoDAO;
import dados.Auditoria;
import dados.Pedido;
import dados.PedidoDeCompra;

import java.util.ArrayList;

public class PedidoService  implements IService<Pedido>{
    private PedidoDAO pedidoDAO = new PedidoDAO();

    @Override
    public Pedido getById(Integer id) {
        return pedidoDAO.getById(id);
    }

    @Override
    public ArrayList<Pedido> getAll() {
        return pedidoDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return pedidoDAO.deleteById(id);
    }

    @Override
    public boolean save(Pedido pedido) {
        return pedidoDAO.save(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return pedidoDAO.update(pedido);
    }

    @Override
    public ArrayList<Auditoria> getAllAuditory() {
        return pedidoDAO.getAllAuditory();
    }
}
