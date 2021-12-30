package controllers.views;

import business.EspacoService;
import controllers.TableButtonsController;
import controllers.modals.CreateEspacoController;
import controllers.modals.EditEspacoController;
import dados.Espaco;
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
import tables.EspacoTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EspacoController implements Initializable {
    EspacoService service = new EspacoService();
    private static ArrayList<Espaco> items;
    private static EspacoTable itemSelecionado;

    @FXML
    public TableView<EspacoTable> tabelaConteudo = new TableView<EspacoTable>();
    ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns

        TableColumn<EspacoTable, String> nomeCol = new TableColumn<EspacoTable, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));

        TableColumn<EspacoTable, Integer> capacidadeCol = new TableColumn<EspacoTable, Integer>("Capacidade");
        capacidadeCol.setCellValueFactory(new PropertyValueFactory("capacidade"));

        TableColumn<EspacoTable, String> ocupadoCol = new TableColumn<EspacoTable, String>("Ocupado");
        ocupadoCol.setCellValueFactory(new PropertyValueFactory("ocupado"));

        TableColumn<EspacoTable, Double> custoReservaCol = new TableColumn<EspacoTable, Double>("Custo da Reserva");
        custoReservaCol.setCellValueFactory(new PropertyValueFactory("custoReserva"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(nomeCol, capacidadeCol, ocupadoCol, custoReservaCol);

        // get data from db
        this.tabelaConteudo.setItems(this.listaDeItems());

        // setando configurações de seleção
        TableView.TableViewSelectionModel<EspacoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<EspacoTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<EspacoTable>() {
            @Override
            public void onChanged(Change<? extends EspacoTable> change) {
                // atualizar o selecionado
                itemSelecionado = change.getList().get(0);
            }
        });
    }

    public ObservableList<EspacoTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<EspacoTable> itemsTableList = new ArrayList<EspacoTable>();
        for (Espaco items : this.items) {
            String isOcupado = items.isOcupado() ? "Sim" : "Não";
            itemsTableList.add(
                    new EspacoTable(
                            items.getId(),
                            items.getNome(),
                            items.getCapacidade(),
                            isOcupado,
                            items.getCustoReserva()
                    )
            );
        }
        return FXCollections.observableArrayList(itemsTableList);
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
        CreateEspacoController controller = new CreateEspacoController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException {
        Espaco item = this.service.getById(itemSelecionado.getId());
        EditEspacoController controller = new EditEspacoController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/espaco-modal.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }
}