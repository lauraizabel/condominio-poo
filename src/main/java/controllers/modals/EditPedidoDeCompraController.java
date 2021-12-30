package controllers.modals;

import business.FornecedorService;
import business.PedidoDeCompraService;
import business.ProdutoService;
import controllers.views.PedidoDeCompraController;
import dados.PedidoDeCompra;
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

public class EditPedidoDeCompraController implements Initializable {
    ProdutoService produtoService = new ProdutoService();
    PedidoDeCompraService service = new PedidoDeCompraService();
    static ArrayList<Produto> items;
    static PedidoDeCompra itemSelected;

    @FXML
    private ComboBox<String> produtoValues;

    public EditPedidoDeCompraController(PedidoDeCompra itemSelected) {
        this.itemSelected = itemSelected;
    }

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.produtoValues.setItems(FXCollections.observableArrayList(getProdutos()));

        Integer produtoIndex = getProdutoIndex(itemSelected.getProduto());
        if ( produtoIndex >= 0 ) {
            this.produtoValues.getSelectionModel().select(produtoIndex);
        }
    }

    private Integer getProdutoIndex(Produto produto) {
        int i;
        for ( i = 0; i < this.produtoValues.getItems().size(); i++ ) {
            if ( this.produtoValues.getItems().get(i).equals(produto.getNome()) ) {
                return i;
            }
        }
        return -1;
    }

    private ObservableList<String> getProdutos() {
        items = this.produtoService.getAll();

        ArrayList<String> itemsList = new ArrayList<String>();
        for ( Produto item: items) {
            itemsList.add(item.getNome());
        }
        return FXCollections.observableArrayList(itemsList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        // pegando campos do fxml
        int pedidoDeCompraIdx = produtoValues.getSelectionModel().getSelectedIndex();
        itemSelected.setProduto(items.get(pedidoDeCompraIdx));

        // Criando novo produto;
        service.update(itemSelected);

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        PedidoDeCompraController controller = new PedidoDeCompraController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
