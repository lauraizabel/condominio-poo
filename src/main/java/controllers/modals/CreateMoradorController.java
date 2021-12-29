package controllers.modals;

import business.MoradorService;
import controllers.views.MoradorController;
import dados.Morador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateMoradorController {
    MoradorService service = new MoradorService();

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

    @FXML
    public void handleSubmit(ActionEvent e) {
        // Criando novo produto
        Morador item = new Morador(
                String.valueOf(nomeValue.getText()),
                String.valueOf(telefoneValue.getText()),
                String.valueOf(emailValue.getText()),
                String.valueOf(cpfValue.getText())
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
        MoradorController controller = new MoradorController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
