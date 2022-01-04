package DAO;

import DAO.implementation.EntityDAO;
import dados.Compra;
import dados.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;

public class UsuarioDAO extends EntityDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findByEmail (String email) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("FROM Usuario WHERE email = :email", Usuario.class);
            query.setParameter("email", email);
            ArrayList<Usuario> usuarios = new ArrayList<Usuario>(query.getResultList());
            return usuarios.get(0);
        } finally {
            this.closeConnection(em);
        }
    }
}
