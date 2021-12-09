package services;

import dados.Carro;
import repositories.CarroRepository;

import java.util.ArrayList;

public class CarroServices implements IService<Carro>{
    private CarroRepository carroRepository = new CarroRepository();

    @Override
    public Carro getById(Integer id) {
        return carroRepository.getById(id);
    }

    @Override
    public ArrayList<Carro> getAll() {
        return carroRepository.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return carroRepository.deleteById(id);
    }

    @Override
    public boolean save(Carro reserva) {
        return carroRepository.save(reserva);
    }

    @Override
    public Carro update(Carro reserva) {
        return carroRepository.update(reserva);
    }
}
