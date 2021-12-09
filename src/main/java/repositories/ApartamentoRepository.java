package repositories;

import java.util.ArrayList;

import DAO.ApartamentoDAO;
import dados.Apartamento;

public class ApartamentoRepository implements IRepository<Apartamento> {
    private ApartamentoDAO apartamentoDAO = new ApartamentoDAO();

    @Override
    public Apartamento getById(Integer id) {
        Apartamento apartamento = apartamentoDAO.getById(id);
        return apartamento;
    }

    @Override
    public ArrayList<Apartamento> getAll() {
        ArrayList<Apartamento> apartamento = apartamentoDAO.getAll();
        return apartamento;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = apartamentoDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Apartamento apartamento) {
        boolean result = apartamentoDAO.save(apartamento);
        return result;
    }

    @Override
    public Apartamento update(Apartamento apartamento) {
        Apartamento apartamentoUpdated = apartamentoDAO.update(apartamento);
        return apartamentoUpdated;
    }

}
