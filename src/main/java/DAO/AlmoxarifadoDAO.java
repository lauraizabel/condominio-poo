package DAO;

import DAO.implementation.EntityDAO;
import dados.Almoxarifado;

import javax.persistence.Query;
import java.util.List;

public class AlmoxarifadoDAO extends EntityDAO<Almoxarifado> {
    public AlmoxarifadoDAO() {super(Almoxarifado.class);}

    public List<Almoxarifado> findByProductId (Integer id) {
        Query query = this.em.createQuery("SELECT * FROM almoxarifado WHERE produto_id = :id");
        query.setParameter("id", id);
        List<Almoxarifado> almoxarifados =  query.getResultList();
        return almoxarifados;
    }
}

