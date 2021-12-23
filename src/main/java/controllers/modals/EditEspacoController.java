package controllers.modals;

import business.EspacoService;
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

public class EditEspacoController implements Initializable {
    EspacoService service = new EspacoService();
    static Espaco itemSelected;

    @FXML
    TextField nomeValue;

    @FXML
    TextField capacidadeValue;

    @FXML
    CheckBox ocupadoValue;

    @FXML
    TextField custoReservaValue;

    @FXML
    private Button submitButton;

    public EditEspacoController(Espaco itemSelected) {
        this.itemSelected = itemSelected;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting existing values
        this.nomeValue.setText(itemSelected.getNome());
        this.capacidadeValue.setText(String.valueOf(itemSelected.getCapacidade()));
        this.ocupadoValue.setSelected(Boolean.valueOf(itemSelected.isOcupado()));
        this.custoReservaValue.setText(String.valueOf(itemSelected.getCustoReserva()));
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        // alterando item usando campos
        itemSelected.setNome(String.valueOf(nomeValue.getText()));
        itemSelected.setOcupado(ocupadoValue.isSelected());
        itemSelected.setCapacidade(Integer.valueOf(capacidadeValue.getText()));
        itemSelected.setCustoReserva(Double.valueOf(custoReservaValue.getText()));

        // Atualizando item;
        service.update(itemSelected);
        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
//        FUncionarioController controller = new FUncionarioController();
//        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
