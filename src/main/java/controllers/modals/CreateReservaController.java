package controllers.modals;

import business.ReservaService;
import controllers.views.ReservaController;
import dados.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class CreateReservaController {
    ReservaService service = new ReservaService();

    @FXML
    TextField idEspacoValue;

    @FXML
    TextField idApartamentoValue;

    @FXML
    TextField dataReservaValue;

    @FXML
    private Button submitButton;

    @FXML
    public void handleSubmit(ActionEvent e) {
        // Criando novo produto
        Reserva item = new Reserva(
                Integer.valueOf(idEspacoValue.getText()),
                Integer.valueOf(idApartamentoValue.getText()),
                Date.valueOf(dataReservaValue.getText())
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
        ReservaController controller = new ReservaController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
