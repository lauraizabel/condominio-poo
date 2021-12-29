package controllers.modals;

import business.FornecedorService;
import business.ProdutoService;
import controllers.views.ProdutoController;
import dados.Fornecedor;
import dados.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditProdutoController implements Initializable {
    FornecedorService fornecedorService = new FornecedorService();
    ProdutoService produtoService = new ProdutoService();
    static ArrayList<Fornecedor> fornecedores;
    static Produto itemSelected;

    @FXML
    private TextField nomeValue;

    @FXML
    private TextField codigoValue;

    @FXML
    private TextField pontoDePedidoValue;

    @FXML
    private ComboBox<String> fornecedoresValues;

    public EditProdutoController(Produto itemSelected) {
        this.itemSelected = itemSelected;
    }

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.fornecedoresValues.setItems(FXCollections.observableArrayList(getFornecedores()));

        // setting existing values
        this.nomeValue.setText(itemSelected.getNome());
        this.codigoValue.setText(itemSelected.getCodigo());
        this.pontoDePedidoValue.setText(itemSelected.getPontoDePedido().toString());

        Integer fornecedorIndex = getFornecedorIndex(itemSelected.getFornecedor());
        if ( fornecedorIndex >= 0 ) {
            this.fornecedoresValues.getSelectionModel().select(fornecedorIndex);
        }
    }

    private Integer getFornecedorIndex(Fornecedor fornecedor) {
        int i;
        for ( i = 0; i < this.fornecedoresValues.getItems().size(); i++ ) {
            if ( this.fornecedoresValues.getItems().get(i).equals(fornecedor.getNome()) ) {
                return i;
            }
        }
        return -1;
    }

    private ObservableList<String> getFornecedores() {
        fornecedores = this.fornecedorService.getAll();

        ArrayList<String> fornecedoresList = new ArrayList<String>();
        for ( Fornecedor fornecedor: fornecedores) {
            fornecedoresList.add(fornecedor.getNome());
        }
        return FXCollections.observableArrayList(fornecedoresList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        Integer quantidade = 0;

        // pegando campos do fxml
        itemSelected.setNome(String.valueOf(nomeValue.getText()));
        itemSelected.setCodigo(String.valueOf(codigoValue.getText()));
        itemSelected.setPontoDePedido(Integer.valueOf(pontoDePedidoValue.getText()));
        int fornecedorIdx = fornecedoresValues.getSelectionModel().getSelectedIndex();
        itemSelected.setFornecedor(fornecedores.get(fornecedorIdx));

        // Criando novo produto;
        produtoService.update(itemSelected);

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
