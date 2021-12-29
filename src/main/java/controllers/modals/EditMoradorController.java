package controllers.modals;

import business.MoradorService;
import controllers.views.MoradorController;
import dados.Morador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMoradorController implements Initializable {
    MoradorService service = new MoradorService();
    static Morador itemSelected;

    @FXML
    TextField nomeValue;

    @FXML
    TextField telefoneValue;

    @FXML
    TextField emailValue;

    @FXML
    TextField cpfValue;

    @FXML
    private Button submitButton;

    public EditMoradorController(Morador itemSelected) { this.itemSelected = itemSelected; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting existing values
        this.nomeValue.setText(itemSelected.getNome());
        this.telefoneValue.setText(itemSelected.getTelefone());
        this.emailValue.setText(itemSelected.getEmail());
        this.cpfValue.setText(itemSelected.getCpf());
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        // alterando item usando campos
        itemSelected.setNome(String.valueOf(nomeValue.getText()));
        itemSelected.setTelefone(String.valueOf(telefoneValue.getText()));
        itemSelected.setEmail(String.valueOf(emailValue.getText()));
        itemSelected.setCpf(String.valueOf(cpfValue.getText()));

        // Atualizando item;
        try {
            service.save(itemSelected);
        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.WARNING, error.getMessage(), ButtonType.CLOSE);
            alert.show();
        }
        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        MoradorController controller = new MoradorController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
