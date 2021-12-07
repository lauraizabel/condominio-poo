package services;

import dados.Reserva;
import repositories.ReservaRepository;

import java.util.List;

public class ReservaService implements IService<Reserva> {
    private ReservaRepository reservaRepository;

    @Override
    public Reserva getById(Integer id) {
        return reservaRepository.getById(id);
    }

    @Override
    public List<Reserva> getAll() {
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
