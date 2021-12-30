package controllers.views;

import business.ProdutoService;
import controllers.TableButtonsController;
import controllers.modals.CreateProdutoController;
import controllers.modals.EditProdutoController;
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
import jdk.jshell.spi.ExecutionControl;
import tables.ProdutoTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable {
    ProdutoService service = new ProdutoService();
    private static ArrayList<Produto> items;
    private static ObservableList<ProdutoTable> tableItems;
    private static ProdutoTable itemSelecionado;

    @FXML
    public TableView<ProdutoTable> tabelaConteudo = new TableView<ProdutoTable>();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns
        TableColumn<ProdutoTable, String> codigoCol = new TableColumn<ProdutoTable, String>("Código");
        codigoCol.setCellValueFactory(new PropertyValueFactory("codigo"));

        TableColumn<ProdutoTable, String> nomeCol = new TableColumn<ProdutoTable, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));

        TableColumn<ProdutoTable, String> fornecedorCol = new TableColumn<ProdutoTable, String>("Fornecedor");
        fornecedorCol.setCellValueFactory(new PropertyValueFactory("fornecedor"));

        TableColumn<ProdutoTable, Integer> quantidadeCol = new TableColumn<ProdutoTable, Integer>("Quantidade");
        quantidadeCol.setCellValueFactory(new PropertyValueFactory("quantidade"));

        TableColumn<ProdutoTable, Integer> pontoDePedidoCol = new TableColumn<ProdutoTable, Integer>("Ponto de Pedido");
        pontoDePedidoCol.setCellValueFactory(new PropertyValueFactory("pontoDePedido"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(codigoCol, nomeCol, fornecedorCol, quantidadeCol, pontoDePedidoCol);

        // get data from db
        tableItems = this.listaDeItems();
        this.tabelaConteudo.setItems(tableItems);

        // setando configurações de seleção
        TableView.TableViewSelectionModel<ProdutoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<ProdutoTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<ProdutoTable>() {
            @Override
            public void onChanged(Change<? extends ProdutoTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<ProdutoTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<ProdutoTable> itemTableList = new ArrayList<ProdutoTable>();
        for ( Produto item: this.items) {
            itemTableList.add(
                new ProdutoTable(
                    item.getId(),
                    item.getNome(),
                    item.getQuantidade(),
                    item.getFornecedor().getNome(),
                    item.getCodigo(),
                    item.getPontoDePedido()
                )
            );
        }
        return FXCollections.observableArrayList(itemTableList);
    }

    public boolean onDelete() {
        Integer itemId = itemSelecionado.getId();
        try {
            service.deleteById(itemId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            reloadItems();
        }
    }

    public void onCreate() throws IOException  {
        CreateProdutoController controller = new CreateProdutoController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Produto item = this.service.getById(itemSelecionado.getId());
        EditProdutoController controller = new EditProdutoController(item);
        this.createModal("Editar item", controller);
    }

    public void onAuditory() throws IOException {
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/produto-modal.fxml"));
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
        for (ProdutoTable item: this.listaDeItems()) {
            tableItems.add(item);
        }
    }

}
