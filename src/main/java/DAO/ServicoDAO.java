package DAO;

import dados.Servico;
import DAO.implementation.EntityDAO;

public class ServicoDAO extends EntityDAO<Servico> {
    public ServicoDAO() {
        super(Servico.class);
    }
}
