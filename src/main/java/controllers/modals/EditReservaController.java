package controllers.modals;

import business.ReservaService;
import controllers.views.ReservaController;
import dados.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EditReservaController implements Initializable {
    ReservaService service = new ReservaService();
    static Reserva itemSelected;

    @FXML
    TextField idEspacoValue;

    @FXML
    TextField idApartamentoValue;

    @FXML
    TextField dataReservaValue;

    @FXML
    private Button submitButton;

    public EditReservaController(Reserva itemSelected) { this.itemSelected = itemSelected; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.idEspacoValue.setText(itemSelected.getIdEspaco().toString());
        this.idApartamentoValue.setText(itemSelected.getIdApartamento().toString());
        this.dataReservaValue.setText(itemSelected.getDataReserva().toString());
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        // alterando item usando campos
        itemSelected.setIdEspaco(Integer.valueOf(idEspacoValue.getText()));
        itemSelected.setIdApartamento(Integer.valueOf(idApartamentoValue.getText()));
        itemSelected.setDataReserva(Date.valueOf(dataReservaValue.getText()));

        // Atualizando item;
        try {
            service.save(itemSelected);
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
