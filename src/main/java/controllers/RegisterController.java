package controllers;

import application.MainApplication;
import business.UsuarioService;
import dados.Usuario;
import enums.Screens;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import validation.validacao;

public class RegisterController {
    UsuarioService usuarioService = new UsuarioService();

    @FXML
    TextField nome;
    @FXML
    TextField confirmSenha;
    @FXML
    TextField email;
    @FXML
    TextField senha;

    @FXML
    protected void goToLogin() {
        MainApplication.changeScene(Screens.LOGIN);
    }

    @FXML
    protected void submit() {
        try {
            String pass = new String(senha.getText());
            String confirmPass = new String(confirmSenha.getText());

            if (!pass.equals(confirmPass)) {
                throw new Exception("SENHAS NÃO CONFEREM");
            }

            if(pass.length() < 4) {
                throw new Exception("INSIRA UMA SENHA COM MAIS DE 4 CARACTERES");
            }

            validacao.validaEmail(email.getText());
            Usuario usuario = new Usuario(nome.getText(), email.getText(), pass);
            usuarioService.save(usuario);
            MainApplication.changeScene(Screens.MAIN);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.CLOSE);
            alert.show();
        }
    }
}
