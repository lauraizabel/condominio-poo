package services;

import DAO.ReservaDAO;
import dados.Reserva;

import java.util.ArrayList;

public class ReservaService implements IService<Reserva> {
    private ReservaDAO reservaDAO = new ReservaDAO();

    @Override
    public Reserva getById(Integer id) {
        return reservaDAO.getById(id);
    }

    @Override
    public ArrayList<Reserva> getAll() {
        return reservaDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return reservaDAO.deleteById(id);
    }

    @Override
    public boolean save(Reserva reserva) {
        return reservaDAO.save(reserva);
    }

    @Override
    public Reserva update(Reserva reserva) {
        return reservaDAO.update(reserva);
    }
}
