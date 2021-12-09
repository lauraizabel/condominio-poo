package services;

import java.util.ArrayList;

import DAO.MoradorDAO;
import dados.Morador;


public class MoradorService implements IService<Morador> {
    private MoradorDAO moradorDAO = new MoradorDAO();

    @Override
    public Morador getById(Integer id) {
        return moradorDAO.getById(id);
    }

    @Override
    public ArrayList<Morador> getAll() {
        return moradorDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return moradorDAO.deleteById(id);
    }

    @Override
    public boolean save(Morador reserva) {
        return moradorDAO.save(reserva);
    }

    @Override
    public Morador update(Morador reserva) {
        return moradorDAO.update(reserva);
    }
}
