package controllers;

import application.MainApplication;
import business.UsuarioService;
import enums.Screens;
import javafx.fxml.FXML;

public class LoginController {
    UsuarioService usuarioService = new UsuarioService();
    @FXML
    protected

    @FXML
    protected void goToRegister() {
        MainApplication.changeScreen(Screens.MAIN);
    }
}
