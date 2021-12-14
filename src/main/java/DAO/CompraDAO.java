package DAO;

import DAO.implementation.EntityDAO;
import dados.Compra;

public class CompraDAO extends EntityDAO<Compra> {
    public CompraDAO() {
        super(Compra.class);
    }
}
