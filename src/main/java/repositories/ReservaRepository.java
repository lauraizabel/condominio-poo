package repositories;

import DAO.ReservaDAO;
import dados.Reserva;

import java.util.ArrayList;
public class ReservaRepository implements IRepository<Reserva> {
    private ReservaDAO reservaDAO;

    @Override
    public Reserva getById(Integer id) {
        Reserva reserva = reservaDAO.getById(id);
        return reserva;
    }

    @Override
    public ArrayList<Reserva> getAll() {
        ArrayList<Reserva> reservas = reservaDAO.getAll();
        return reservas;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = reservaDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Reserva reserva) {
        boolean result = reservaDAO.save(reserva);
        return result;
    }

    @Override
    public Reserva update(Reserva reserva) {
        Reserva reservaUpdated = reservaDAO.update(reserva);
        return reservaUpdated;
    }
}
