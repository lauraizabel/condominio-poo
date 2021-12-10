package DAO;

import dados.Pedido;
import DAO.implementation.EntityDAO;

public class PedidoDAO extends EntityDAO<Pedido> {
    public PedidoDAO() {
        super(Pedido.class);
    }
}
