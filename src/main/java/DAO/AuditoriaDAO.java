package DAO;

import dados.Auditoria;
import DAO.implementation.EntityDAO;

public class AuditoriaDAO extends EntityDAO<Auditoria> {
    public AuditoriaDAO() {
        super(Auditoria.class);
    }
}
