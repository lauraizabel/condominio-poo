package controllers.modals;

import business.CarroServices;
import controllers.views.CarroController;
import dados.Carro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCarroController {
    CarroServices service = new CarroServices();

    @FXML
    TextField modeloValue;

    @FXML
    TextField placaValue;

    @FXML
    private Button submitButton;

    @FXML
    public void handleSubmit(ActionEvent e) {
        // Criando novo produto
        Carro item = new Carro(
                String.valueOf(modeloValue.getText()),
                String.valueOf(placaValue.getText())
        );

        try {
            service.save(item);
        } catch (Exception error) {
            error.printStackTrace();
        }

        this.finish();
    }

    private void finish() {
        // atualiza conteúdo
        CarroController controller = new CarroController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
