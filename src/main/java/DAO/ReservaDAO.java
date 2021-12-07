package DAO;

import dados.Reserva;
import DAO.implementation.EntityDAO;

public class ReservaDAO extends EntityDAO<Reserva> {
    public ReservaDAO() {
        super(Reserva.class);
    }
}
