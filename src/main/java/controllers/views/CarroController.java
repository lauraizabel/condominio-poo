package controllers.views;

import business.CarroServices;
import controllers.TableButtonsController;
import controllers.modals.CreateCarroController;
import controllers.modals.CreateFornecedorController;
import controllers.modals.EditCarroController;
import controllers.modals.EditFornecedorController;
import dados.Carro;
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
import tables.CarroTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarroController implements Initializable  {
    CarroServices service = new CarroServices();
    private static ArrayList<Carro> items;
    private static CarroTable itemSelecionado;

    @FXML
    public TableView<CarroTable> tabelaConteudo = new TableView<>();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns

        TableColumn<CarroTable, String> modeloCol = new TableColumn<>("Modelo");
        modeloCol.setCellValueFactory(new PropertyValueFactory("modelo"));

        TableColumn<CarroTable, String> placaCol = new TableColumn<>("Placa");
        placaCol.setCellValueFactory(new PropertyValueFactory("placa"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(modeloCol, placaCol);

        // get data from db
        this.tabelaConteudo.setItems(this.listaDeItems());

        // setando configurações de seleção
        TableView.TableViewSelectionModel<CarroTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<CarroTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<CarroTable>() {
            @Override
            public void onChanged(Change<? extends CarroTable> change) {
                // atualizar o selecionado
                itemSelecionado = change.getList().get(0);
            }
        });
    }

    public ObservableList<CarroTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<CarroTable> carroTableList = new ArrayList<CarroTable>();
        for ( Carro carro: this.items) {
            carroTableList.add(
                    new CarroTable(
                            carro.getId(),
                            carro.getModelo(),
                            carro.getPlaca()
                    )
            );
        }
        return FXCollections.observableArrayList(carroTableList);
    }

    public boolean onDelete() {
        // deletando
        Integer itemCode = itemSelecionado.getId();
        try {
            service.deleteById(itemCode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void onCreate() throws IOException {
        CreateCarroController controller = new CreateCarroController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Carro item = this.service.getById(itemSelecionado.getId());
        EditCarroController controller = new EditCarroController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/carro-modal.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    public void reloadItems() {
        // How to reload ?
//        this.tabelaConteudo.setItems(this.listaDeServicos());  -> does not work
    }
}
