package controllers.modals;

import business.FornecedorService;
import controllers.views.FornecedorController;
import dados.Fornecedor;
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

public class EditFornecedorController implements Initializable {
    FornecedorService service = new FornecedorService();
    static Fornecedor itemSelected;

    @FXML
    TextField nomeValue;

    @FXML
    TextField cnpjValue;

    @FXML
    TextField enderecoValue;

    @FXML
    TextField telefoneValue;

    @FXML
    TextField emailValue;

    @FXML
    private Button submitButton;

    public EditFornecedorController(Fornecedor itemSelected) {
        this.itemSelected = itemSelected;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // setting existing values
        this.nomeValue.setText(itemSelected.getNome());
        this.cnpjValue.setText(itemSelected.getCnpj());
        this.enderecoValue.setText(itemSelected.getEndereco());
        this.telefoneValue.setText(itemSelected.getTelefone());
        this.emailValue.setText(itemSelected.getEmail());
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        // alterando item usando campos
        itemSelected.setNome(String.valueOf(nomeValue.getText()));
        itemSelected.setCnpj(String.valueOf(cnpjValue.getText()));
        itemSelected.setEndereco(String.valueOf(enderecoValue.getText()));
        itemSelected.setTelefone(String.valueOf(telefoneValue.getText()));
        itemSelected.setEmail(String.valueOf(emailValue.getText()));

        // Atualizando item;
        try {
            service.update(itemSelected);
        } catch (Exception error) {
            Alert alert = new Alert(Alert.AlertType.WARNING, error.getMessage(), ButtonType.CLOSE);
            alert.show();
        }
        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        FornecedorController controller = new FornecedorController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
