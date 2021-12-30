package DAO;

import DAO.implementation.EntityDAO;
import dados.PedidoDeCompra;

import javax.persistence.Query;
import java.util.ArrayList;

public class PedidoDeCompraDAO extends EntityDAO<PedidoDeCompra> {

    public PedidoDeCompraDAO() {
        super(PedidoDeCompra.class);
    }

    public ArrayList<PedidoDeCompra> getByProductId(Integer produtoId) {
        Query query = this.em.createQuery("from PedidoDeCompra WHERE produto_id=:id");
        query.setParameter("id", produtoId);

        // convertendo para ArrayList para ser um tipo aceito pelo hibernate
        ArrayList<PedidoDeCompra> result = new ArrayList<PedidoDeCompra>(query.getResultList());
        return result;
    }
}
