package DAO;

import DAO.implementation.EntityDAO;
import dados.Almoxarifado;
import dados.Produto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class AlmoxarifadoDAO extends EntityDAO<Almoxarifado> {
    public AlmoxarifadoDAO() {super(Almoxarifado.class);}

    public ArrayList<Almoxarifado> findByProductId (Integer produtoId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("from Almoxarifado WHERE produto_id=:id");
            query.setParameter("id", produtoId);

            // convertendo para ArrayList para ser um tipo aceito pelo hibernate
            List<Almoxarifado> almoxarifados = query.getResultList();
            ArrayList<Almoxarifado> result = new ArrayList<Almoxarifado>(almoxarifados);

            return result;
        } finally {
            em.close();
        }
    }
}

