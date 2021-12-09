package repositories;

import DAO.ServicoDAO;
import dados.Servico;

import java.util.ArrayList;

public class ServicoRepository implements IRepository<Servico>{
    private ServicoDAO servicoDAO;

    @Override
    public Servico getById(Integer id) {
        Servico servico= servicoDAO.getById(id);
        return servico;
    }

    @Override
    public ArrayList<Servico> getAll() {
        ArrayList<Servico> servicos = servicoDAO.getAll();
        return servicos;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = servicoDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Servico servico) {
        boolean result = servicoDAO.save(servico);
        return result;
    }

    @Override
    public Servico update(Servico servico) {
        Servico servicoUpdated = servicoDAO.update(servico);
        return servicoUpdated;
    }
}
