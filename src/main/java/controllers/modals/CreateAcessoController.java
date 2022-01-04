package controllers.modals;

import business.AcessoPermitidoService;
import business.ApartamentoService;
import controllers.views.AcessoPermitidoController;
import dados.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateAcessoController implements Initializable {
    AcessoPermitidoService service = new AcessoPermitidoService();
    ApartamentoService apartamentoService = new ApartamentoService();
    private static ArrayList<Apartamento> apartamentos;

    @FXML
    TextField nomeValue;

    @FXML
    TextField cpfValue;

    @FXML
    TextField emailValue;

    @FXML
    TextField telefoneValue;

    @FXML
    CheckBox permitidoValue;

    @FXML
    ComboBox<Integer> apartamentosValues = new ComboBox<Integer>();

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        apartamentosValues.setItems(FXCollections.observableArrayList(getApartamentos()));
    }

    private ObservableList<Integer> getApartamentos() {
        this.apartamentos = this.apartamentoService.getAll();

        ArrayList<Integer> aptList = new ArrayList<Integer>();
        for ( Apartamento apartamento: this.apartamentos) {
            aptList.add(apartamento.getNumApartamento());
        }

        return FXCollections.observableArrayList(aptList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) throws Exception {
        Integer quantidade = 0;
        Integer aptIdx = Integer.valueOf(apartamentosValues.getSelectionModel().getSelectedIndex());

        // Criando novo produto
        AcessoPermitido acessoPermitido = new AcessoPermitido(
            String.valueOf(nomeValue.getText()),
            String.valueOf(cpfValue.getText()),
            apartamentos.get(aptIdx),
            String.valueOf(telefoneValue.getText()),
            String.valueOf(emailValue.getText()),
            Boolean.valueOf(permitidoValue.getText())
        );

        service.save(acessoPermitido);
        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        AcessoPermitidoController controller = new AcessoPermitidoController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
