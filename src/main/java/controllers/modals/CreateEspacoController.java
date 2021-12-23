package controllers.modals;

import business.EspacoService;
import business.FornecedorService;
import dados.Espaco;
import dados.Fornecedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateEspacoController {
    EspacoService service = new EspacoService();

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

    @FXML
    public void handleSubmit(ActionEvent e) {
        // Criando novo produto
        Espaco item = new Espaco(
            String.valueOf(nomeValue.getText()),
            Integer.valueOf(capacidadeValue.getText()),
            Boolean.valueOf(ocupadoValue.isSelected()),
            Double.valueOf(custoReservaValue.getText())
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
//        EspacoController controller = new EspacoController();
//        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
