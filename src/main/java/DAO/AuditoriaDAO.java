package DAO;

import DAO.implementation.EntityDAO;
import dados.Auditoria;

public class AuditoriaDAO extends EntityDAO<Auditoria> {
    public AuditoriaDAO() {
        super(Auditoria.class);
    }
}
