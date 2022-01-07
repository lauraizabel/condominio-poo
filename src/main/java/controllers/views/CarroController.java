package controllers.views;

import business.CarroServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.TableButtonsController;
import controllers.modals.CreateCarroController;
import controllers.modals.CreateFornecedorController;
import controllers.modals.EditCarroController;
import controllers.modals.EditFornecedorController;
import dados.Auditoria;
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
import tables.AuditoriaTable;
import tables.CarroTable;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

        // setando configurações de seleção
        TableView.TableViewSelectionModel<CarroTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
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
                    //gambiarra que pode ser alterada pra quem quiser e tiver paciência
                    new CarroTable(
                            carro.getId(),
                            carro.getModelo(),
                            carro.getPlaca(),
                            "",
                            ""
                    )
            );
        }
        return FXCollections.observableArrayList(carroTableList);
    }

    public ObservableList<CarroTable> listaItemsAuditoria (ArrayList<Carro> carroArrayList,  ArrayList<Auditoria> dadosAuditoria) {
        ArrayList<CarroTable> carroTableList = new ArrayList<CarroTable>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < carroArrayList.size(); i++) {
            Integer t;
            if(carroArrayList.get(i).getId() == null) {
                t = 0;
            } else {
                t = carroArrayList.get(i).getId();
            }
            carroTableList.add(
                    new CarroTable(
                            t,
                            carroArrayList.get(i).getModelo(),
                            carroArrayList.get(i).getPlaca(),
                            df.format(dadosAuditoria.get(i).getDataAlteracao()),
                            dadosAuditoria.get(i).getTipoAuditoria().getAuditoria()
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
        createModalAuditory();
    }

    private void createModalAuditory () throws IOException {
        ArrayList<Carro> carroList = new ArrayList<>();
        ArrayList<Auditoria> dadosAuditoria = this.service.getAllAuditory();
        ObjectMapper objectMapper = new ObjectMapper();

        for (Auditoria dados: dadosAuditoria) {
            Carro carro = objectMapper.readValue(dados.getCamposAlterados(), Carro.class);
            carroList.add(carro);
        }

        ObservableList<CarroTable> carroArrayList = listaItemsAuditoria(carroList, dadosAuditoria);

        TableView table = new TableView();

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());

        TableColumn modelo = new TableColumn("Modelo");
        modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));

        TableColumn placa = new TableColumn("Placa");
        placa.setCellValueFactory(new PropertyValueFactory<>("placa"));

        TableColumn dataAlteracao = new TableColumn("Data de Alteração");
        dataAlteracao.setCellValueFactory(new PropertyValueFactory<>("dataAlteracao"));

        TableColumn tipoAlteracao = new TableColumn("Tipo da Alteração");
        tipoAlteracao.setCellValueFactory(new PropertyValueFactory<>("tipoAlteracao"));

        table.getColumns().addAll(modelo, placa, dataAlteracao, tipoAlteracao);
        table.setItems(carroArrayList);

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
        // removendo itens de trás para frente (para a remoção não interferir no index)
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
