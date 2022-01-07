package DAO;

import dados.Funcionario;
import DAO.implementation.EntityDAO;

public class FuncionarioDAO extends EntityDAO<Funcionario> {
    public FuncionarioDAO() {
        super(Funcionario.class);
    }
}
