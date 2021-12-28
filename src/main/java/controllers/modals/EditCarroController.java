package controllers.modals;

import business.CarroServices;
import controllers.views.CarroController;
import dados.Carro;
import dados.Espaco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCarroController implements Initializable {
    CarroServices service = new CarroServices();
    static Carro itemSelected;

    @FXML
    TextField modeloValue;

    @FXML
    TextField placaValue;

    @FXML
    private Button submitButton;

    public EditCarroController(Carro itemSelected) {
        this.itemSelected = itemSelected;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting existing values
        this.modeloValue.setText(itemSelected.getModelo());
        this.placaValue.setText(itemSelected.getPlaca());
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        // alterando item usando campos
        itemSelected.setModelo(String.valueOf(modeloValue.getText()));
        itemSelected.setPlaca(String.valueOf(placaValue.getText()));

        // Atualizando item;
        service.update(itemSelected);
        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        CarroController controller = new CarroController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
