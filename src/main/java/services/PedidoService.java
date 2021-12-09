package services;

import DAO.PedidoDAO;
import dados.Pedido;

import java.util.ArrayList;

public class PedidoService  implements IService<Pedido>{
    private PedidoDAO PedidoDAO = new PedidoDAO();

    @Override
    public Pedido getById(Integer id) {
        return PedidoDAO.getById(id);
    }

    @Override
    public ArrayList<Pedido> getAll() {
        return PedidoDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return PedidoDAO.deleteById(id);
    }

    @Override
    public boolean save(Pedido pedido) {
        return PedidoDAO.save(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return PedidoDAO.update(pedido);
    }
}
