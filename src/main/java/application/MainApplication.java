package application;

import business.CarroServices;
import dados.Carro;
import enums.Screens;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private static Stage stage;
    private static Scene mainScene;
    private static Scene loginScene;
    private static Scene registerScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
         mainScene = new Scene(fxmlLoader.load(), 900, 600);

        FXMLLoader fxmlLogin = new FXMLLoader(MainApplication.class.getResource("login-view.fxml"));
        loginScene = new Scene(fxmlLogin.load(), 900, 600);

        FXMLLoader fxmlRegister = new FXMLLoader(MainApplication.class.getResource("register-view.fxml"));
        registerScene = new Scene(fxmlRegister.load(), 900, 600);
        //remove a borda - para usar precisa ter um meio de mudar a posição dela e colocar botões de ação, como fechar em outro lugar
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("Condomínio POO");
        //stage.setMaximized(true);
        stage.setResizable(false);
        //TODO - trocar para login depois
        stage.setScene(mainScene);
        stage.show();
    }

    public static void changeScene(Screens screen) {
        switch (screen){
            case REGISTER:
                stage.setScene(registerScene);
                break;
            case MAIN:
                stage.setScene(mainScene);
                break;
            case LOGIN:
                stage.setScene(loginScene);
                break;
        }
    }

    public static void main(String[] args) {
        CarroServices carro = new CarroServices();
        carro.getAllAuditory().get(0).getRev();
        launch();
    }
}