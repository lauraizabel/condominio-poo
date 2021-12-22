package controllers.modals;

import business.FornecedorService;
import business.ProdutoService;
import dados.Fornecedor;
import dados.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateProdutoController implements Initializable {
    FornecedorService fornecedorService = new FornecedorService();
    ProdutoService service = new ProdutoService();
    static ArrayList<Fornecedor>fornecedores;

    @FXML
    TextField nomeValue;

    @FXML
    TextField codigoValue;

    @FXML
    TextField pontoDePedidoValue;

    @FXML
    ComboBox<String> fornecedoresValues;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fornecedoresValues.setItems(FXCollections.observableArrayList(getFornecedores()));
    }

    private ObservableList<String> getFornecedores() {
        this.fornecedores = this.fornecedorService.getAll();

        ArrayList<String> fornecedoresList = new ArrayList<String>();
        for ( Fornecedor fornecedor: this.fornecedores) {
            fornecedoresList.add(fornecedor.getNome());
        }

        return FXCollections.observableArrayList(fornecedoresList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        Integer quantidade = 0;
        Integer fornecedorIdx = Integer.valueOf(fornecedoresValues.getSelectionModel().getSelectedIndex());

        // Criando novo produto
        Produto produto = new Produto(
            String.valueOf(nomeValue.getText()),
            fornecedores.get(fornecedorIdx),
            String.valueOf(codigoValue.getText()),
            Integer.valueOf(pontoDePedidoValue.getText()),
            quantidade
        );
        service.save(produto);
    };
}
