package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainController {
    @FXML
    public GridPane conteudoView;

    @FXML
    public void switchById(ActionEvent event) throws IOException {
        try {
            // pegando id para identificar botão que ciclou
            String menuItemID = ((Button) event.getSource()).getId();
            String className = menuItemID.substring(0, 1).toUpperCase() + menuItemID.substring(1) + "Controller";

            // limpando conteúdo do GridPane
            conteudoView.getChildren().clear();

            // caminho do arquivo fxml ( da view que lista qualquer tipo de item )
            String filePath = "/application/views/items-view.fxml";

            // Adicionando novo conteúdo
            FXMLLoader loaderView = new FXMLLoader(getClass().getResource(filePath));

            // criando controller correspondente pelo id da opção selecionada
            try {
                Class<?> clazz = Class.forName("controllers.views." + className);
                Constructor<?> constructor = clazz.getConstructor();
                loaderView.setController(constructor.newInstance());
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
//                System.out.println("ERRO [MAIN - on create controller ]: " + e);
                e.printStackTrace();
            }

            conteudoView.getChildren().add((Node) loaderView.load());

            FXMLLoader tableButtonView = new FXMLLoader(getClass().getResource("/application/table-buttons.fxml"));
            tableButtonView.setController( new TableButtonsController(className));

            conteudoView.getChildren().add((Node) tableButtonView.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}