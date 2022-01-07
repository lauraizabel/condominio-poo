package business;

import DAO.ReservaDAO;
import DAO.UsuarioDAO;
import dados.Auditoria;
import dados.Reserva;
import dados.Usuario;
import validation.bcrypt;

import java.util.ArrayList;

public class UsuarioService implements IService<Usuario>{
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public Usuario getById(Integer id) {
        return usuarioDAO.getById(id);
    }

    @Override
    public ArrayList<Usuario> getAll() {
        return usuarioDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return usuarioDAO.deleteById(id);
    }

    @Override
    public boolean save(Usuario usuario) {
        usuario.setPassword(bcrypt.hash(usuario.getPassword()));
        return usuarioDAO.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }

    @Override
    public ArrayList<Auditoria> getAllAuditory() {
        return usuarioDAO.getAllAuditory();
    }

    public boolean login (String email, String password) {
        Usuario findedUser = usuarioDAO.findByEmail(email);
        if(findedUser == null) return false;
        return bcrypt.verifyHash(password, findedUser.getPassword());
    }
}
