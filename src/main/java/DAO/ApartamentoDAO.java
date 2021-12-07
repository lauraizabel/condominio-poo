package DAO;

import dados.Apartamento;
import DAO.implementation.EntityDAO;

public class ApartamentoDAO extends EntityDAO<Apartamento> {
    public ApartamentoDAO() {
        super(Apartamento.class);
    }
}
