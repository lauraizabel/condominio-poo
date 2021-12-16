package DAO;

import DAO.implementation.EntityDAO;
import dados.Compra;

import javax.persistence.Query;
import java.util.ArrayList;

public class CompraDAO extends EntityDAO<Compra> {
    public CompraDAO() {
        super(Compra.class);
    }

    public ArrayList<Compra> findByProductId (Integer id) {
        Query query = this.em.createQuery("FROM Compra WHERE produto_id = :id");
        query.setParameter("id", id);
        ArrayList<Compra> compras = new ArrayList<Compra>(query.getResultList());
        return compras;
    }
}
