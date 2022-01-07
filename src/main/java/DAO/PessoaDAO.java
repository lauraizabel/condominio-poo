package DAO;

import dados.Pessoa;
import DAO.implementation.EntityDAO;

public class PessoaDAO extends EntityDAO<Pessoa> {
    public PessoaDAO() {
        super(Pessoa.class);
    }
}
