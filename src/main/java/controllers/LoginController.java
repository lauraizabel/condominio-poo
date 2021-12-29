package controllers;

import application.MainApplication;
import business.UsuarioService;
import enums.Screens;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class LoginController {
    UsuarioService usuarioService = new UsuarioService();

    @FXML
    TextField email;
    @FXML
    TextField password;

    @FXML
    protected void goToRegister() {
        MainApplication.changeScene(Screens.REGISTER);
    }

    @FXML
    protected void submit() {
        String pass = password.getText();
        String em = email.getText();

        boolean correctFields = usuarioService.login(em, pass);

        if(correctFields) {
            MainApplication.changeScene(Screens.MAIN);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Senha ou e-mail inválidos.", ButtonType.CLOSE);
            alert.show();
        }
    }
}
