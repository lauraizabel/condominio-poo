package DAO;

import dados.Produto;
import DAO.implementation.EntityDAO;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends EntityDAO<Produto> {
    public ProdutoDAO() {
        super(Produto.class);
    }

    // capturar todos pelo nome do produto -> para contagem
    public ArrayList<Produto> getByName (String produtctName) {
        EntityManager em = getEntityManager();
        try {
            Session session = em.unwrap(Session.class);
            Query query = session.createQuery("from Produto where name=:name ")
                    .setParameter("name ", produtctName);

            // convertendo para ArrayList para ser um tipo aceito pelo hibernate
            List<Produto> result = query.getResultList();
            ArrayList<Produto> produtos = new ArrayList<Produto>(result);

            return produtos;
        } finally {
            em.close();
        }
    }
}
