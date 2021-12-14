package DAO;

import DAO.implementation.EntityDAO;
import dados.Almoxarifado;
import dados.Compra;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO extends EntityDAO<Compra> {
    public CompraDAO() {
        super(Compra.class);
    }

    public ArrayList<Compra> findByProductId (Integer id) {
        Query query = this.em.createQuery("SELECT * FROM compra WHERE produto_id = :id");
        query.setParameter("id", id);
        ArrayList<Compra> compras =  new ArrayList<>(query.getResultList());
        return compras;
    }
}
