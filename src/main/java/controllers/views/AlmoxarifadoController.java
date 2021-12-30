package controllers.views;

import business.AlmoxarifadoService;
import controllers.TableButtonsController;
import controllers.modals.CreateAlmoxarifadoController;
import controllers.modals.CreateFornecedorController;
import controllers.modals.EditAlmoxarifadoController;
import controllers.modals.EditFornecedorController;
import dados.Almoxarifado;
import dados.Fornecedor;
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
import tables.AlmoxarifadoTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AlmoxarifadoController implements Initializable {
    AlmoxarifadoService service = new AlmoxarifadoService();
    private static ArrayList<Almoxarifado> items;
    private static AlmoxarifadoTable itemSelecionado;

    @FXML
    public TableView<AlmoxarifadoTable> tabelaConteudo = new TableView<AlmoxarifadoTable>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns

        TableColumn<AlmoxarifadoTable, String> produtoCol = new TableColumn<AlmoxarifadoTable, String>("Produto");
        produtoCol.setCellValueFactory(new PropertyValueFactory("produto"));

        TableColumn<AlmoxarifadoTable, String> funcionarioCol = new TableColumn<AlmoxarifadoTable, String>("Funcionário");
        funcionarioCol.setCellValueFactory(new PropertyValueFactory("funcionario"));

        TableColumn<AlmoxarifadoTable, Integer> quantidadeAdicionadaCol = new TableColumn<AlmoxarifadoTable, Integer>("Quantidade Adcionada");
        quantidadeAdicionadaCol.setCellValueFactory(new PropertyValueFactory("quantidadeAdicionada"));

        TableColumn<AlmoxarifadoTable, Integer> quantidadeRemovidaCol = new TableColumn<AlmoxarifadoTable, Integer>("Quantidade Removida");
        quantidadeRemovidaCol.setCellValueFactory(new PropertyValueFactory("quantidadeAdicionada"));

        TableColumn<AlmoxarifadoTable, Date> dataAlteracaoCol = new TableColumn<AlmoxarifadoTable, Date>("Data");
        dataAlteracaoCol.setCellValueFactory(new PropertyValueFactory("dataAlteracao"));


        // add columns
        this.tabelaConteudo.getColumns().setAll(produtoCol, funcionarioCol, quantidadeAdicionadaCol, quantidadeRemovidaCol, dataAlteracaoCol);

        // get data from db
        this.tabelaConteudo.setItems(this.listaDeItems());

        // setando configurações de seleção
        TableView.TableViewSelectionModel<AlmoxarifadoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<AlmoxarifadoTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<AlmoxarifadoTable>() {
            @Override
            public void onChanged(Change<? extends AlmoxarifadoTable> change) {
                // atualizar o selecionado
                itemSelecionado = change.getList().get(0);
            }
        });
    }

    public ObservableList<AlmoxarifadoTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<AlmoxarifadoTable> almoxarifadoTableList = new ArrayList<AlmoxarifadoTable>();
        for ( Almoxarifado almoxarifado: this.items) {
            almoxarifadoTableList.add(
                new AlmoxarifadoTable(
                        almoxarifado.getId(),
                        almoxarifado.getQuantidadeAdicionada(),
                        almoxarifado.getQuantidadeRemovida(),
                        almoxarifado.getProduto().getNome(),
                        almoxarifado.getFuncionario().getNome(),
                        almoxarifado.getDataAlteracao()
                )
            );
        }
        return FXCollections.observableArrayList(almoxarifadoTableList);
    }

    public boolean onDelete() {
        // deletando
        Integer itemCode = itemSelecionado.getId();
        Almoxarifado item = this.service.getById(itemCode);

        if ( item != null ) {
            Integer itemId = item.getId();
            System.out.println("Found: " + itemId);
//          service.deleteById(itemId);
        } else {
            System.out.println("Not found.");
        }

        return true;
    }

    public void onCreate() throws IOException  {
        CreateAlmoxarifadoController controller = new CreateAlmoxarifadoController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Almoxarifado item = this.service.getById(itemSelecionado.getId());
        EditAlmoxarifadoController controller = new EditAlmoxarifadoController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/almoxarifado-modal.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}
