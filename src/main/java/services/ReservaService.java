package services;

import dados.Reserva;
import repositories.ReservaRepository;

import java.util.ArrayList;

public class ReservaService implements IService<Reserva> {
    private ReservaRepository reservaRepository = new ReservaRepository();

    @Override
    public Reserva getById(Integer id) {
        return reservaRepository.getById(id);
    }

    @Override
    public ArrayList<Reserva> getAll() {
        return reservaRepository.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return reservaRepository.deleteById(id);
    }

    @Override
    public boolean save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva update(Reserva reserva) {
        return reservaRepository.update(reserva);
    }
}
