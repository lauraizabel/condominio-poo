package business;

import DAO.ServicoDAO;
import dados.Servico;

import java.util.ArrayList;

public class ServicoService implements IService<Servico> {
    private ServicoDAO servicoDAO = new ServicoDAO();

    @Override
    public Servico getById(Integer id) {
        return servicoDAO.getById(id);
    }

    @Override
    public ArrayList<Servico> getAll() {
        return servicoDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return servicoDAO.deleteById(id);
    }

    @Override
    public boolean save(Servico servico) { return servicoDAO.save(servico); }

    @Override
    public Servico update(Servico servico) {
        return servicoDAO.update(servico);
    }
}
