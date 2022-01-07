package controllers.views;

import business.ApartamentoService;
import controllers.TableButtonsController;
import controllers.modals.CreateApartamentoController;
import controllers.modals.EditApartamentoController;
import dados.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import tables.CarroTable;


public class ApartamentoController implements Initializable {
  ApartamentoService service = new ApartamentoService();
  private static ObservableList<ApartamentoTable> tableItems;
  
  private static ArrayList<Apartamento> items;
  private static ApartamentoTable itemSelecionado;
  
  public TableView<ApartamentoTable> tabelaConteudo = new TableView<>();
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // create columns
    TableColumn<ApartamentoTable, String> blocoColumn = new TableColumn<>("Bloco");
    blocoColumn.setCellValueFactory(new PropertyValueFactory("bloco"));
  
    TableColumn<ApartamentoTable, Integer> andarColumn = new TableColumn<>("Andar");
    andarColumn.setCellValueFactory(new PropertyValueFactory("andar"));
    
    TableColumn<ApartamentoTable, Integer> numeroColumn = new TableColumn<>("Número");
    numeroColumn.setCellValueFactory(new PropertyValueFactory("numero"));
  
    TableColumn<ApartamentoTable, String> moradoresColumn = new TableColumn<>("Morador");
    moradoresColumn.setCellValueFactory(new PropertyValueFactory("moradores"));
  
    TableColumn<ApartamentoTable, String> carrosColumn = new TableColumn<>("Carro");
    carrosColumn.setCellValueFactory(new PropertyValueFactory("carros"));
  
  
    // add columns
    this.tabelaConteudo.getColumns().setAll(blocoColumn, andarColumn, numeroColumn, moradoresColumn, carrosColumn);
  
    // get data from db
    tableItems = this.listaDeItems(this.service.getAll());
    this.tabelaConteudo.setItems(tableItems);
  
    // setando configurações de seleção
    TableView.TableViewSelectionModel<ApartamentoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
    selectionModel.setSelectionMode(SelectionMode.SINGLE);

    ObservableList<ApartamentoTable> selectedItems = selectionModel.getSelectedItems();
    selectedItems.addListener(new ListChangeListener<ApartamentoTable>() {
      @Override
      public void onChanged(Change<? extends ApartamentoTable> change) {
        // atualizar o selecionado
        if ( change.getList().size() > 0 ) {
          itemSelecionado = change.getList().get(0);
        }
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
              this.ListToStringMoradores(item.getMoradores()),
              this.ListToStringCarros(item.getCarros())
          )
      );
    }
    return FXCollections.observableArrayList(itemTableList);
  }

  public String ListToStringMoradores(List<Morador> list) {
    String result = new String();
    for (Morador item: list) {
      result += item.getNome() + ", " ;
    }
    return result.substring(0, result.length() - 2);
  }

  public String ListToStringCarros(List<Carro> list) {
    String result = new String();
    for (Carro item: list) {
      result += item.getPlaca() + ", " ;
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
  
  public void onCreate() throws IOException {
    CreateApartamentoController controller = new CreateApartamentoController();
    this.createModal("Criar novo item", controller);
  }
  
  public void onEdit() throws IOException {
    Apartamento               item       = this.service.getById(itemSelecionado.getId());
    EditApartamentoController controller = new EditApartamentoController(item);
    this.createModal("Editar item", controller);
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

  public void cleanTableContent() {
    // removendo itens de trás para frente (para a remoção não interferir no index)
    Integer tableItemsSize = tableItems.size();
    for (int i = tableItemsSize - 1; i >= 0; i--) {
      tableItems.remove(i);
    }
  }
  private void populateTableContent() {
    // adicionando novos itens
    for (ApartamentoTable item: this.listaDeItems(this.service.getAll())) {
      tableItems.add(item);
    }
  }
}
