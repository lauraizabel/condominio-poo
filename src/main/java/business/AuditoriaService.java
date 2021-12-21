package business;

import java.util.ArrayList;

import DAO.AuditoriaDAO;
import dados.Auditoria;

public class AuditoriaService implements IService<Auditoria> {
    private AuditoriaDAO auditoriaDAO = new AuditoriaDAO();

    @Override
    public Auditoria getById(Integer id) {
        return auditoriaDAO.getById(id);
    }

    @Override
    public ArrayList<Auditoria> getAll() {
        return auditoriaDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return auditoriaDAO.deleteById(id);
    }

    @Override
    public boolean save(Auditoria auditoria) throws Exception {
        return auditoriaDAO.save(auditoria);
    }

    @Override
    public Auditoria update(Auditoria auditoria) {
        return auditoriaDAO.update(auditoria);
    }
    
}
