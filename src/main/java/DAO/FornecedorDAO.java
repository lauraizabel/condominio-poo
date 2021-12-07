package DAO;

import dados.Fornecedor;
import DAO.implementation.EntityDAO;

public class FornecedorDAO extends EntityDAO<Fornecedor> {
    public FornecedorDAO() {
        super(Fornecedor.class);
    }
}
