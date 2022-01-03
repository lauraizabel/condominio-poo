package controllers.views;

import business.PedidoDeCompraService;
import controllers.TableButtonsController;
import controllers.modals.CreatePedidoDeCompraController;
import controllers.modals.EditPedidoDeCompraController;
import controllers.modals.EditProdutoController;
import dados.PedidoDeCompra;
import dados.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tables.CarroTable;
import tables.PedidoDeCompraTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PedidoDeCompraController implements Initializable {
    PedidoDeCompraService service = new PedidoDeCompraService();
    private static ArrayList<PedidoDeCompra> items;
    private static ObservableList<PedidoDeCompraTable> tableItems;
    private static PedidoDeCompraTable itemSelecionado;

    @FXML
    public TableView<PedidoDeCompraTable> tabelaConteudo = new TableView<PedidoDeCompraTable>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns
        TableColumn<PedidoDeCompraTable, Produto> produtoCol = new TableColumn<PedidoDeCompraTable, Produto>("Produto");
        produtoCol.setCellValueFactory(new PropertyValueFactory("produto"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(produtoCol);

        // get data from db
        this.tabelaConteudo.setItems(this.listaDeItems());

        // setando configurações de seleção
        TableView.TableViewSelectionModel<PedidoDeCompraTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<PedidoDeCompraTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<PedidoDeCompraTable>() {
            @Override
            public void onChanged(Change<? extends PedidoDeCompraTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<PedidoDeCompraTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<PedidoDeCompraTable> itemTableList = new ArrayList<PedidoDeCompraTable>();
        for ( PedidoDeCompra item: this.items) {
            itemTableList.add(
                new PedidoDeCompraTable(
                    item.getProduto().getNome(),
                    item.getId()
                )
            );
        }
        return FXCollections.observableArrayList(itemTableList);
    }

    public boolean onDelete() {
        // deletando
        Integer itemCode = itemSelecionado.getId();
        try {
            service.deleteById(itemCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.reloadItems();
        }

        return true;
    }

    public void onCreate() throws IOException  {
        CreatePedidoDeCompraController controller = new CreatePedidoDeCompraController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        PedidoDeCompra item = this.service.getById(itemSelecionado.getId());
        EditPedidoDeCompraController controller = new EditPedidoDeCompraController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/pedidoDeCompra-modal.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public void reloadItems() {
        this.cleanTableContent();
        this.populateTableContent();
    }

    public void cleanTableContent() {
        // removendo itens de trás para frente (para a remoção não interferir no index)
        Integer tableItemsSize = tableItems.size();
        for (int i = tableItemsSize - 1; i >= 0; i--) {
            tableItems.remove(i);
        }
    }

    private void populateTableContent() {
        // adicionando novos itens
        for (PedidoDeCompraTable item: this.listaDeItems()) {
            tableItems.add(item);
        }
    }

}
