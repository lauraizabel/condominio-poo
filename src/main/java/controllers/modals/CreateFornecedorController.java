package controllers.modals;

import business.FornecedorService;
import dados.Fornecedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateFornecedorController {
    FornecedorService service = new FornecedorService();

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

    @FXML
    public void handleSubmit(ActionEvent e) {
        // Criando novo produto
        Fornecedor item = new Fornecedor(
            String.valueOf(nomeValue.getText()),
            String.valueOf(cnpjValue.getText()),
            String.valueOf(enderecoValue.getText()),
            String.valueOf(telefoneValue.getText()),
            String.valueOf(emailValue.getText())
        );

        try {
            service.save(item);
        } catch (Exception error) {
            error.printStackTrace();
        }

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
//        FornecedorController controller = new FornecedorController();
//        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
