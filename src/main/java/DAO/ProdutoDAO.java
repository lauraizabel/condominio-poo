package DAO;

import dados.Produto;
import DAO.implementation.EntityDAO;

public class ProdutoDAO extends EntityDAO<Produto> {
    public ProdutoDAO() {
        super(Produto.class);
    }
}
