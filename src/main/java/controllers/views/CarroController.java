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
import javafx.scene.Group;
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
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarroController implements Initializable  {
    CarroServices service = new CarroServices();
    private static ArrayList<Carro> items;
    private static ObservableList<CarroTable> tableItems;
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
        tableItems = this.listaDeItems(this.service.getAll());
        this.tabelaConteudo.setItems(tableItems);

        // setando configura????es de sele????o
        TableView.TableViewSelectionModel<CarroTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudan??a nos selecionados
        ObservableList<CarroTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<CarroTable>() {
            @Override
            public void onChanged(Change<? extends CarroTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<CarroTable> listaDeItems(ArrayList<Carro> carroArrayList) {
        this.items = carroArrayList;
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
        } finally {
            this.reloadItems();
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

    public void onAuditory() throws IOException {
        this.createModalAuditory();
    }

    private void createModalAuditory () throws IOException {
        ObservableList<CarroTable> carroArrayList = listaDeItems(this.service.getAllAuditory());

        TableView table = new TableView();

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());

        TableColumn modelo = new TableColumn("Modelo");
        modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn placa = new TableColumn("Placa");
        placa.setCellValueFactory(new PropertyValueFactory<>("placa"));

        table.getColumns().addAll(modelo, placa);
        table.setItems(carroArrayList);
        System.out.println(carroArrayList.size());
        ((Group) scene.getRoot()).getChildren().addAll(table);

        stage.setScene(scene);
        stage.setTitle("Auditoria");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
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
        this.cleanTableContent();
        this.populateTableContent();
    }

    public void cleanTableContent() {
        // removendo itens de tr??s para frente (para a remo????o n??o interferir no index)
        Integer tableItemsSize = tableItems.size();
        for (int i = tableItemsSize - 1; i >= 0; i--) {
            tableItems.remove(i);
        }
    }

    private void populateTableContent() {
        // adicionando novos itens
        for (CarroTable item: this.listaDeItems(this.service.getAll())) {
            tableItems.add(item);
        }
    }
}
