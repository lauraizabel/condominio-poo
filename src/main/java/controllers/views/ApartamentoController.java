package controllers.views;

import business.ApartamentoService;
import controllers.TableButtonsController;
import controllers.modals.CreateApartamentoController;
import controllers.modals.EditApartamentoController;
import dados.Apartamento;
import dados.Carro;
import dados.Pessoa;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import tables.ApartamentoTable;



public class ApartamentoController implements Initializable {
  ApartamentoService service = new ApartamentoService();
  private static ObservableList<ApartamentoTable> tableItems;
  
  private static ArrayList<Apartamento> items;
  private static ApartamentoTable itemSelecionado;
  
  public TableView<ApartamentoTable> tabelaConteudo = new TableView<>();
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // create columns
    TableColumn<ApartamentoTable, String> funcionarioCol = new TableColumn<>("Funcionário");
    funcionarioCol.setCellValueFactory(new PropertyValueFactory("funcionario"));
  
    TableColumn<ApartamentoTable, String> produtoCol = new TableColumn<>("Produto");
    produtoCol.setCellValueFactory(new PropertyValueFactory("produtos"));
  
    // add columns
    this.tabelaConteudo.getColumns().setAll(funcionarioCol, produtoCol);
  
    // get data from db
    tableItems = this.listaDeItems(this.service.getAll());
    this.tabelaConteudo.setItems(tableItems);
  
    // setando configurações de seleção
    TableView.TableViewSelectionModel<ApartamentoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
    selectionModel.setSelectionMode(SelectionMode.SINGLE);
  
    // escutar mudança nos selecionados
    ObservableList<ApartamentoTable> selectedItems = selectionModel.getSelectedItems();
    selectedItems.addListener((ListChangeListener<ApartamentoTable>) change -> {
      // atualizar o selecionado
      if (change.getList().size() > 0) {
        itemSelecionado = change.getList().get(0);
      }
    });
  }
  
  public ObservableList<ApartamentoTable> listaDeItems(ArrayList<Apartamento> apartamentos) {
    items = apartamentos;
    ArrayList<ApartamentoTable> itemTableList = new ArrayList<>();
    for (Apartamento item: items) {
      itemTableList.add(
          new ApartamentoTable(
              item.getId(),
              item.getBloco(),
              item.getAndar(),
              item.getNumApartamento(),
              item.getMoradores().stream().map(Pessoa::getNome).collect(Collectors.joining(", ")),
              item.getCarros().stream().map(Carro::getModelo).collect(Collectors.joining(", "))
          )
      );
    }
    return FXCollections.observableArrayList(itemTableList);
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
  
  public void onCreate() throws IOException {
    CreateApartamentoController controller = new CreateApartamentoController();
    this.createModal("Criar novo item", controller);
  }
  
  public void onEdit() throws IOException {
    Apartamento               item       = this.service.getById(itemSelecionado.getId());
    EditApartamentoController controller = new EditApartamentoController(item);
    this.createModal("Editar item", controller);
  }
  
  public void onAuditory() throws IOException {
    this.createModalAuditory();
  }
  
  private void createModalAuditory() {
    ObservableList<ApartamentoTable> apartamentos = listaDeItems(this.service.getAllAuditory());
  
    TableView table = new TableView();
  
    Stage stage = new Stage();
    Scene scene = new Scene(new Group());
  
    TableColumn<ApartamentoTable, String> apartamentoColumn = new TableColumn<>("Morador");
    apartamentoColumn.setCellValueFactory(new PropertyValueFactory("moradores"));
  
    TableColumn<ApartamentoTable, String> carroColumn = new TableColumn<>("Carro");
    carroColumn.setCellValueFactory(new PropertyValueFactory("carros"));
  
    // add columns
    table.getColumns().setAll(apartamentoColumn, carroColumn);
    table.setItems(apartamentos);
    ((Group) scene.getRoot()).getChildren().addAll(table);
  
    stage.setScene(scene);
    stage.setTitle("Auditoria");
    stage.initModality(Modality.WINDOW_MODAL);
    stage.show();
  }
  
  private void createModal(String title, Object controller) throws IOException {
    FXMLLoader loader = new FXMLLoader(
        TableButtonsController.class.getResource("/application/modals/apartamento-modal.fxml"));
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
  
  private void cleanTableContent() {
    int tableItemsSize = tableItems.size();
    if (tableItemsSize > 0) {
      tableItems.subList(0, tableItemsSize).clear();
    }
  }
  
  private void populateTableContent() {
    tableItems.addAll(this.listaDeItems(this.service.getAll()));
  }
}
