package business;

import DAO.AuditoriaDAO;
import dados.Auditoria;

import java.util.ArrayList;

public class AuditoriaService implements IService<Auditoria>{
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
    public boolean save(Auditoria auditoria) {
        return auditoriaDAO.save(auditoria);
    }

    @Override
    public Auditoria update(Auditoria auditoria) {
        return auditoriaDAO.update(auditoria);
    }

    @Override
    public ArrayList<Auditoria> getAllAuditory() {
        return auditoriaDAO.getAllAuditory();
    }
}
