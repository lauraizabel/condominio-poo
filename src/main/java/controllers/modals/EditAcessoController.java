package controllers.modals;

import business.AcessoPermitidoService;
import business.ApartamentoService;
import business.FornecedorService;
import business.ProdutoService;
import controllers.views.ProdutoController;
import dados.AcessoPermitido;
import dados.Apartamento;
import dados.Fornecedor;
import dados.Produto;
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

public class EditAcessoController implements Initializable {
    AcessoPermitidoService acessoPermitidoService = new AcessoPermitidoService();
    ApartamentoService apartamentoService = new ApartamentoService();
    static ArrayList<Apartamento> apartamentos;
    static AcessoPermitido itemSelected;

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

    public EditAcessoController(AcessoPermitido itemSelected) {
        this.itemSelected = itemSelected;
    }

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.apartamentosValues.setItems(FXCollections.observableArrayList(getApartamentos()));

        // setting existing values
        this.nomeValue.setText(itemSelected.getNome());
        this.cpfValue.setText(itemSelected.getCpf());
        this.emailValue.setText(itemSelected.getEmail());
        this.telefoneValue.setText(itemSelected.getTelefone());
        this.permitidoValue.setSelected(itemSelected.isPermitido());


        Integer aptIndex = getApartamentoIndex(itemSelected.getApartamento());
        if ( aptIndex >= 0 ) {
            this.apartamentosValues.getSelectionModel().select(aptIndex);
        }
    }

    private Integer getApartamentoIndex(Apartamento apartamento) {
        int i;
        for ( i = 0; i < this.apartamentosValues.getItems().size(); i++ ) {
            if ( this.apartamentosValues.getItems().get(i).equals(apartamento.getNumApartamento()) ) {
                return i;
            }
        }
        return -1;
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
    public void handleSubmit(ActionEvent e) {
        Integer quantidade = 0;

        // pegando campos do fxml
        itemSelected.setNome(String.valueOf(nomeValue.getText()));
        itemSelected.setCpf(String.valueOf(cpfValue.getText()));
        itemSelected.setEmail(String.valueOf(emailValue.getText()));
        itemSelected.setTelefone(String.valueOf(telefoneValue.getText()));
        int aptIdx = apartamentosValues.getSelectionModel().getSelectedIndex();
        itemSelected.setApartamento(apartamentos.get(aptIdx));
        itemSelected.setPermitido(permitidoValue.isSelected());


        // Criando novo produto;
        acessoPermitidoService.update(itemSelected);

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        ProdutoController controller = new ProdutoController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
