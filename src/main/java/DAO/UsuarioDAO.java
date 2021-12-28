package DAO;

import DAO.implementation.EntityDAO;
import dados.PedidoDeCompra;
import dados.Usuario;

import javax.persistence.Query;
import java.util.ArrayList;

public class UsuarioDAO extends EntityDAO<Usuario> {
    public UsuarioDAO() {
        super(Usuario.class);
    }

    public Usuario findByEmail(String email) {
        Query query = this.em.createQuery("FROM Usuario WHERE email = :email");
        query.setParameter("email", email.toLowerCase());
        ArrayList<Usuario> result = new ArrayList<Usuario>(query.getResultList());
        return result.get(0);
    }


}
