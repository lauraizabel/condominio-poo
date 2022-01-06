package controllers.views;

import business.CompraService;
import controllers.TableButtonsController;
import controllers.modals.CreateAlmoxarifadoController;
import controllers.modals.CreateCompraController;
import controllers.modals.EditAlmoxarifadoController;
import controllers.modals.EditCompraController;
import dados.Compra;
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
import tables.AlmoxarifadoTable;
import tables.CompraTable;
import tables.PedidoDeCompraTable;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CompraController implements Initializable {
    CompraService service = new CompraService();
    private static ArrayList<Compra> items;
    private static ObservableList<CompraTable> tableItems;
    private static CompraTable itemSelecionado;

    @FXML
    public TableView<CompraTable> tabelaConteudo = new TableView<CompraTable>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns
        TableColumn<CompraTable, String> produtoCol = new TableColumn<CompraTable, String>("Produto");
        produtoCol.setCellValueFactory(new PropertyValueFactory("produto"));

        TableColumn<CompraTable, String> funcionarioCol = new TableColumn<CompraTable, String>("Funcionário");
        funcionarioCol.setCellValueFactory(new PropertyValueFactory("funcionario"));

        TableColumn<CompraTable, Integer> quantidadeCol = new TableColumn<CompraTable, Integer>("Quantidade");
        quantidadeCol.setCellValueFactory(new PropertyValueFactory("quantidade"));

        TableColumn<CompraTable, Integer> valorUnitarioCol = new TableColumn<CompraTable, Integer>("Valor Unitário");
        valorUnitarioCol.setCellValueFactory(new PropertyValueFactory("valorUnitario"));

        TableColumn<CompraTable, Date> dataAlteracaoCol = new TableColumn<CompraTable, Date>("Data");
        dataAlteracaoCol.setCellValueFactory(new PropertyValueFactory("dataAlteracao"));


        // add columns
        this.tabelaConteudo.getColumns().setAll(produtoCol, funcionarioCol, valorUnitarioCol, dataAlteracaoCol, quantidadeCol);

        // get data from db
        tableItems = this.listaDeItems(this.service.getAll());
        this.tabelaConteudo.setItems(tableItems);

        // setando configurações de seleção
        TableView.TableViewSelectionModel<CompraTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<CompraTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<CompraTable>() {
            @Override
            public void onChanged(Change<? extends CompraTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<CompraTable> listaDeItems(ArrayList<Compra> compraArrayList) {
        this.items = compraArrayList;
        ArrayList<CompraTable> compraTableList = new ArrayList<CompraTable>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for ( Compra compra: this.items) {
            compraTableList.add(
                new CompraTable(
                        compra.getId(),
                        compra.getQuantidade(),
                        compra.getValorUnitario(),
                        compra.getProduto().getNome(),
                        compra.getFuncionario().getNome(),
                        df.format(compra.getDataAlteracao())
                )
            );
        }
        return FXCollections.observableArrayList(compraTableList);
    }

    public boolean onDelete() {
        // deletando
        Integer itemId = itemSelecionado.getId();
        try {
            service.deleteById(itemId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.reloadItems();
        }
    }

    public void onCreate() throws IOException  {
        CreateCompraController controller = new CreateCompraController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Compra item = this.service.getById(itemSelecionado.getId());
        EditCompraController controller = new EditCompraController(item);
        this.createModal("Editar item", controller);
    }

    public void onAuditory() throws IOException {
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/compra-modal.fxml"));
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
        for (CompraTable item: this.listaDeItems(this.service.getAll())) {
            tableItems.add(item);
        }
    }

}
