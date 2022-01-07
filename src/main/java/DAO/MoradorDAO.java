package DAO;

import dados.Morador;
import DAO.implementation.EntityDAO;

public class MoradorDAO extends EntityDAO<Morador> {
    public MoradorDAO() {
        super(Morador.class);
    }
}
