package controllers.views;

import business.FuncionarioService;
import business.PedidoService;
import business.ProdutoService;
import controllers.TableButtonsController;
import controllers.modals.*;
import dados.Funcionario;
import dados.Pedido;
import dados.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tables.MoradorTable;
import tables.PedidoDeCompraTable;
import tables.PedidoTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PedidoController implements Initializable {
    PedidoService service = new PedidoService();
    private static ObservableList<PedidoTable> tableItems;

    private static ArrayList<Pedido> items;
    private static PedidoTable itemSelecionado;

    @FXML
    public TableView<PedidoTable> tabelaConteudo = new TableView<PedidoTable>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns
        TableColumn<PedidoTable, String> funcionarioCol = new TableColumn<PedidoTable, String>("Funcionário");
        funcionarioCol.setCellValueFactory(new PropertyValueFactory("funcionario"));

        TableColumn<PedidoTable, String> produtoCol = new TableColumn<PedidoTable, String>("Produto");
        produtoCol.setCellValueFactory(new PropertyValueFactory("produtos"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(funcionarioCol, produtoCol);

        // get data from db
        tableItems = this.listaDeItems(this.service.getAll());
        this.tabelaConteudo.setItems(tableItems);

        // setando configurações de seleção
        TableView.TableViewSelectionModel<PedidoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<PedidoTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<PedidoTable>() {
            @Override
            public void onChanged(Change<? extends PedidoTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<PedidoTable> listaDeItems(ArrayList<Pedido> pedidoArrayList) {
        this.items = pedidoArrayList;
        ArrayList<PedidoTable> itemTableList = new ArrayList<PedidoTable>();
        for ( Pedido item: this.items) {
            itemTableList.add(
                new PedidoTable(
                    item.getId(),
                    item.getRequerente().getNome(),
                    this.ListToString(item.getProdutos())
                )
            );
        }
        return FXCollections.observableArrayList(itemTableList);
    }

    public String ListToString(List<Produto> list) {
        String result = new String();
        for (Produto item: list) {
            result += item.getNome() + ", " ;
        }
        return result.substring(0, result.length() - 2);
    }

    public boolean onDelete() {
        // deletando
        Integer itemId = itemSelecionado.getId();
        try {
            service.deleteById(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.reloadItems();
        }
        return true;
    }

    public void onCreate() throws IOException  {
        CreatePedidoController controller = new CreatePedidoController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Pedido item = this.service.getById(itemSelecionado.getId());
        EditPedidoController controller = new EditPedidoController(item);
        this.createModal("Editar item", controller);
    }

    public void onAuditory() throws IOException {
        this.createModalAuditory();
    }

    private void createModalAuditory () throws IOException {
        ObservableList<PedidoTable> pedidoArray = listaDeItems(this.service.getAllAuditory());

        TableView table = new TableView();

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());

        TableColumn<PedidoTable, String> funcionarioCol = new TableColumn<PedidoTable, String>("Funcionário");
        funcionarioCol.setCellValueFactory(new PropertyValueFactory("funcionario"));

        TableColumn<PedidoTable, String> produtoCol = new TableColumn<PedidoTable, String>("Produto");
        produtoCol.setCellValueFactory(new PropertyValueFactory("produtos"));

        // add columns
        table.getColumns().setAll(funcionarioCol, produtoCol);
        table.setItems(pedidoArray);
        ((Group) scene.getRoot()).getChildren().addAll(table);

        stage.setScene(scene);
        stage.setTitle("Auditoria");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/pedido-modal.fxml"));
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
        for (PedidoTable item: this.listaDeItems(this.service.getAll())) {
            tableItems.add(item);
        }
    }

}
