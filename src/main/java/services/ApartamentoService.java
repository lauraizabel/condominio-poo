package services;

import dados.Apartamento;
import repositories.ApartamentoRepository;

import java.util.ArrayList;

public class ApartamentoService  implements IService<Apartamento>{
    private ApartamentoRepository apartamentoRepository = new ApartamentoRepository();

    @Override
    public Apartamento getById(Integer id) {
        return apartamentoRepository.getById(id);
    }

    @Override
    public ArrayList<Apartamento> getAll() {
        return apartamentoRepository.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return apartamentoRepository.deleteById(id);
    }

    @Override
    public boolean save(Apartamento reserva) {
        return apartamentoRepository.save(reserva);
    }

    @Override
    public Apartamento update(Apartamento reserva) {
        return apartamentoRepository.update(reserva);
    }
}
