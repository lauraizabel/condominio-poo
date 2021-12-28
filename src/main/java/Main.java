import business.*;
import dados.*;

public class Main {
      public static void main(String[] args) throws Exception {
            UsuarioService usuarioService = new UsuarioService();

            Usuario usuario = new Usuario("Laura", "12345", "lauraizabel@gmail.com");

//            usuarioService.save(usuario);
            System.out.println(usuarioService.login(usuario));
      }
}
