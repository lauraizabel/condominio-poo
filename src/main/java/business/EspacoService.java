package business;

import DAO.EspacoDAO;
import dados.Espaco;

import java.util.ArrayList;

public class EspacoService implements IService<Espaco> {
    private EspacoDAO espacoDAO = new EspacoDAO();

    @Override
    public Espaco getById(Integer id) {
        Espaco espaco = espacoDAO.getById(id);
        return espaco;
    }

    @Override
    public ArrayList<Espaco> getAll() {
        ArrayList<Espaco> espacos = espacoDAO.getAll();
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
