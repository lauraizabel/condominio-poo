package services;

import java.util.ArrayList;

import dados.Morador;
import repositories.MoradorRespository;


public class MoradorService implements IService<Morador> {
    private MoradorRespository moradorRespository = new MoradorRespository();

    @Override
    public Morador getById(Integer id) {
        return moradorRespository.getById(id);
    }

    @Override
    public ArrayList<Morador> getAll() {
        return moradorRespository.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return moradorRespository.deleteById(id);
    }

    @Override
    public boolean save(Morador reserva) {
        return moradorRespository.save(reserva);
    }

    @Override
    public Morador update(Morador reserva) {
        return moradorRespository.update(reserva);
    }
}
