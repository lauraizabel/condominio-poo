package services;

import DAO.EspacoDAO;
import dados.Espaco;
import repositories.IRepository;

import java.util.List;

public class EspacoService implements IRepository<Espaco> {
    private EspacoDAO espacoDAO;

    @Override
    public Espaco getById(Integer id) {
        Espaco espaco = espacoDAO.getById(id);
        return espaco;
    }

    @Override
    public List<Espaco> getAll() {
        List<Espaco> espacos = espacoDAO.getAll();
        return espacos;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = espacoDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Espaco espaco) {
        boolean result = espacoDAO.save(espaco);
        return result;
    }

    @Override
    public Espaco update(Espaco espaco) {
        Espaco espacoUpdated = espacoDAO.update(espaco);
        return espacoUpdated;
    }

}
