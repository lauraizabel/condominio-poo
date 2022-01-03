package controllers.views;

import business.ReservaService;
import controllers.TableButtonsController;
import controllers.modals.CreateReservaController;
import controllers.modals.EditReservaController;
import dados.Reserva;
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
import tables.ReservaTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservaController implements Initializable {
    ReservaService service = new ReservaService();
    private static ArrayList<Reserva> items;
    private static ObservableList<ReservaTable> tableItems;
    private static ReservaTable itemSelecionado;

    @FXML
    public TableView<ReservaTable> tabelaConteudo = new TableView<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<ReservaTable, Integer> idEspacoCol = new TableColumn<>("ID Espaco");
        idEspacoCol.setCellValueFactory(new PropertyValueFactory("idEspaco"));

        TableColumn<ReservaTable, Integer> idApartamentoCol = new TableColumn<>("ID Apartamento");
        idApartamentoCol .setCellValueFactory(new PropertyValueFactory("idApartamento"));

        TableColumn<ReservaTable, String> dataReservaCol = new TableColumn<>("Data Reserva");
        dataReservaCol .setCellValueFactory(new PropertyValueFactory("dataReserva"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(idEspacoCol, idApartamentoCol, dataReservaCol);

        // get data from db
        tableItems = this.listaDeItems();
        this.tabelaConteudo.setItems(tableItems);

        // setando configurações de seleção
        TableView.TableViewSelectionModel<ReservaTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<ReservaTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<ReservaTable>() {
            @Override
            public void onChanged(Change<? extends ReservaTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<ReservaTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<ReservaTable> reservaTableList = new ArrayList<ReservaTable>();
        for ( Reserva reserva: this.items) {
            reservaTableList.add(
                    new ReservaTable(
                            reserva.getId(),
                            reserva.getIdEspaco(),
                            reserva.getIdApartamento(),
                            reserva.getDataReserva().toString()
                    )
            );
        }
        return FXCollections.observableArrayList(reservaTableList);
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
        CreateReservaController controller = new CreateReservaController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Reserva item = this.service.getById(itemSelecionado.getId());
        EditReservaController controller = new EditReservaController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/reserva-modal.fxml"));
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
        for (ReservaTable item: this.listaDeItems()) {
            tableItems.add(item);
        }
    }
}
