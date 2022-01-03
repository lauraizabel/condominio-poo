package controllers.views;

import business.AlmoxarifadoService;
import controllers.TableButtonsController;
import controllers.modals.CreateAlmoxarifadoController;
import controllers.modals.EditAlmoxarifadoController;
import dados.Almoxarifado;
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
import tables.AcessoTable;
import tables.AlmoxarifadoTable;
import tables.PedidoDeCompraTable;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AlmoxarifadoController implements Initializable {
    AlmoxarifadoService service = new AlmoxarifadoService();
    private static ArrayList<Almoxarifado> items;
    private static ObservableList<AlmoxarifadoTable> tableItems;
    private static AlmoxarifadoTable itemSelecionado;

    @FXML
    public TableView<AlmoxarifadoTable> tabelaConteudo = new TableView<AlmoxarifadoTable>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns
        System.out.println("INITIALAZING");

        TableColumn<AlmoxarifadoTable, String> produtoCol = new TableColumn<AlmoxarifadoTable, String>("Produto");
        produtoCol.setCellValueFactory(new PropertyValueFactory("produto"));

        TableColumn<AlmoxarifadoTable, String> funcionarioCol = new TableColumn<AlmoxarifadoTable, String>("Funcionário");
        funcionarioCol.setCellValueFactory(new PropertyValueFactory("funcionario"));

        TableColumn<AlmoxarifadoTable, Integer> quantidadeAdicionadaCol = new TableColumn<AlmoxarifadoTable, Integer>("Quantidade Adcionada");
        quantidadeAdicionadaCol.setCellValueFactory(new PropertyValueFactory("quantidadeAdicionada"));

        TableColumn<AlmoxarifadoTable, Integer> quantidadeRemovidaCol = new TableColumn<AlmoxarifadoTable, Integer>("Quantidade Removida");
        quantidadeRemovidaCol.setCellValueFactory(new PropertyValueFactory("quantidadeRemovida"));

        TableColumn<AlmoxarifadoTable, Date> dataAlteracaoCol = new TableColumn<AlmoxarifadoTable, Date>("Data");
        dataAlteracaoCol.setCellValueFactory(new PropertyValueFactory("dataAlteracao"));


        // add columns
        this.tabelaConteudo.getColumns().setAll(produtoCol, funcionarioCol, quantidadeAdicionadaCol, quantidadeRemovidaCol, dataAlteracaoCol);

        // get data from db
        this.tabelaConteudo.setItems(this.listaDeItems(this.service.getAll()));

        // setando configurações de seleção
        TableView.TableViewSelectionModel<AlmoxarifadoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<AlmoxarifadoTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<AlmoxarifadoTable>() {
            @Override
            public void onChanged(Change<? extends AlmoxarifadoTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<AlmoxarifadoTable> listaDeItems(ArrayList<Almoxarifado> almoxarifadoArrayList) {
        this.items = almoxarifadoArrayList;
        ArrayList<AlmoxarifadoTable> almoxarifadoTableList = new ArrayList<AlmoxarifadoTable>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for ( Almoxarifado almoxarifado: this.items) {
            almoxarifadoTableList.add(
                new AlmoxarifadoTable(
                        almoxarifado.getId(),
                        almoxarifado.getQuantidadeAdicionada(),
                        almoxarifado.getQuantidadeRemovida(),
                        almoxarifado.getProduto().getNome(),
                        almoxarifado.getFuncionario().getNome(),
                        df.format(almoxarifado.getDataAlteracao())
                )
            );
        }
        return FXCollections.observableArrayList(almoxarifadoTableList);
    }

    public boolean onDelete() {
        // deletando
        Integer itemId = itemSelecionado.getId();

        try {
            service.deleteById(itemId);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            this.reloadItems();
        }
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

    public void onAuditory() throws IOException {
        this.createModalAuditory();
    }

    private void createModalAuditory () throws IOException {
        ObservableList<AlmoxarifadoTable> almoxarifadoArray = listaDeItems(this.service.getAllAuditory());

        TableView table = new TableView();

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());

        TableColumn<AlmoxarifadoTable, String> produtoCol = new TableColumn<AlmoxarifadoTable, String>("Produto");
        produtoCol.setCellValueFactory(new PropertyValueFactory("produto"));

        TableColumn<AlmoxarifadoTable, String> funcionarioCol = new TableColumn<AlmoxarifadoTable, String>("Funcionário");
        funcionarioCol.setCellValueFactory(new PropertyValueFactory("funcionario"));

        TableColumn<AlmoxarifadoTable, Integer> quantidadeAdicionadaCol = new TableColumn<AlmoxarifadoTable, Integer>("Quantidade Adcionada");
        quantidadeAdicionadaCol.setCellValueFactory(new PropertyValueFactory("quantidadeAdicionada"));

        TableColumn<AlmoxarifadoTable, Integer> quantidadeRemovidaCol = new TableColumn<AlmoxarifadoTable, Integer>("Quantidade Removida");
        quantidadeRemovidaCol.setCellValueFactory(new PropertyValueFactory("quantidadeRemovida"));

        TableColumn<AlmoxarifadoTable, Date> dataAlteracaoCol = new TableColumn<AlmoxarifadoTable, Date>("Data");
        dataAlteracaoCol.setCellValueFactory(new PropertyValueFactory("dataAlteracao"));


        // add columns
        table.getColumns().setAll(produtoCol, funcionarioCol, quantidadeAdicionadaCol, quantidadeRemovidaCol, dataAlteracaoCol);
        table.setItems(almoxarifadoArray);
        ((Group) scene.getRoot()).getChildren().addAll(table);

        stage.setScene(scene);
        stage.setTitle("Auditoria");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
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
        for (AlmoxarifadoTable item: this.listaDeItems(this.service.getAll())) {
            tableItems.add(item);
        }
    }

}
