package DAO;

import dados.Carro;
import DAO.implementation.EntityDAO;

public class CarroDAO extends EntityDAO<Carro> {
    public CarroDAO() {
        super(Carro.class);
    }
}