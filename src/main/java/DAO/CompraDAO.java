package DAO;

import DAO.implementation.EntityDAO;
import dados.Compra;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class CompraDAO extends EntityDAO<Compra> {
    public CompraDAO() {
        super(Compra.class);
    }

    public ArrayList<Compra> findByProductId (Integer id) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("from Compra WHERE produto_id=:id");
            query.setParameter("id", id);
            ArrayList<Compra> compras = new ArrayList<Compra>(query.getResultList());
            return compras;
        } finally {
            this.closeConnection(em);
        }
    }
}
