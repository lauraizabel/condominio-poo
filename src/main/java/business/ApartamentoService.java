package business;

import DAO.ApartamentoDAO;
import dados.Almoxarifado;
import dados.Apartamento;

import java.util.ArrayList;

public class ApartamentoService  implements IService<Apartamento>{
    private ApartamentoDAO apartamentoDAO = new ApartamentoDAO();

    @Override
    public Apartamento getById(Integer id) {
        return apartamentoDAO.getById(id);
    }

    @Override
    public ArrayList<Apartamento> getAll() {
        return apartamentoDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return apartamentoDAO.deleteById(id);
    }

    @Override
    public boolean save(Apartamento apartamento) {
        return apartamentoDAO.save(apartamento);
    }

    @Override
    public Apartamento update(Apartamento reserva) {
        return apartamentoDAO.update(reserva);
    }

    @Override
    public ArrayList<Apartamento> getAllAuditory() {
        return apartamentoDAO.getAllAuditory();
    }
}
