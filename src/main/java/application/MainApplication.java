package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        //remove a borda - para usar precisa ter um meio de mudar a posição dela e colocar botões de ação, como fechar em outro lugar
        //stage.initStyle(StageStyle.UNDECORATED);

        stage.setTitle("Condomínio POO");
        //stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}