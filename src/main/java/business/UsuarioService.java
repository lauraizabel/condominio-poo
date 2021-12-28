package business;

import DAO.UsuarioDAO;
import dados.Usuario;
import validation.Bcrypt;

import java.util.ArrayList;

public class UsuarioService implements IService<Usuario> {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private Bcrypt bCrypt = new Bcrypt();

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
        String newPass = bCrypt.hashPassword(usuario.getSenha());
        usuario.setSenha(newPass);
        usuario.setEmail(usuario.getEmail().toLowerCase());
        return usuarioDAO.save(usuario);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioDAO.update(usuario);
    }

    @Override
    public ArrayList<Usuario> getAllAuditory() {
        return usuarioDAO.getAllAuditory();
    }

    public boolean login (Usuario usuario) {
        Usuario findedUsuario = usuarioDAO.findByEmail(usuario.getEmail());

        if (findedUsuario == null) {
            return false;
        }

        return bCrypt.checkPass(usuario.getSenha(), findedUsuario.getSenha());
    }
}
