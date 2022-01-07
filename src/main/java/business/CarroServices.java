package business;

import DAO.CarroDAO;
import dados.Auditoria;
import dados.Carro;
import enums.TipoAuditoria;
import validation.utils;

import java.util.ArrayList;
import java.util.Date;

public class CarroServices implements IService<Carro> {
    private CarroDAO carroDAO = new CarroDAO();
    private AuditoriaService auditoriaService = new AuditoriaService();

    @Override
    public Carro getById(Integer id) {
        return carroDAO.getById(id);
    }

    @Override
    public ArrayList<Carro> getAll() {
        return carroDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        try {
            Carro carro = this.getById(id);
            String carString = utils.createObjectMapper().writeValueAsString(carro);
            Auditoria auditoria = new Auditoria(new Date(), carString, TipoAuditoria.DELETADO, Carro.class.getName());
            this.auditoriaService.save(auditoria);
            return carroDAO.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean save(Carro carro) {
        try {
            String carString = utils.createObjectMapper().writeValueAsString(carro);
            Auditoria auditoria = new Auditoria(new Date(), carString, TipoAuditoria.CRIADO, Carro.class.getName());
            this.auditoriaService.save(auditoria);
            return carroDAO.save(carro);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Carro update(Carro carro) {
        try {
            String carString = utils.createObjectMapper().writeValueAsString(carro);
            Auditoria auditoria = new Auditoria(new Date(), carString, TipoAuditoria.EDITADO, Carro.class.getName());
            this.auditoriaService.save(auditoria);
            carroDAO.update(carro);
            return carro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carro;
        }
    }

    @Override
    public ArrayList<Auditoria> getAllAuditory() {
        return carroDAO.getAllAuditory();
    }
}
