package controllers.modals;

import business.PedidoDeCompraService;
import business.ProdutoService;
import controllers.views.ProdutoController;
import dados.PedidoDeCompra;
import dados.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatePedidoDeCompraController implements Initializable {
    ProdutoService produtoService = new ProdutoService();
    PedidoDeCompraService service = new PedidoDeCompraService();
    static ArrayList<Produto> items;
    static PedidoDeCompra itemSelected;

    @FXML
    private ComboBox<String> produtoValues;

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.produtoValues.setItems(FXCollections.observableArrayList(getProdutos()));
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
        Integer produtoIdx = Integer.valueOf(produtoValues.getSelectionModel().getSelectedIndex());

        PedidoDeCompra pedidoDeCompra = new PedidoDeCompra(
            items.get(produtoIdx)
        );
        service.save(pedidoDeCompra);

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
