package controllers.views;

import business.FuncionarioService;
import controllers.TableButtonsController;
import controllers.modals.CreateFuncionarioController;
import controllers.modals.EditFuncionarioController;
import dados.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
import tables.FuncionarioTable;

public class FuncionarioController implements Initializable {
  FuncionarioService service = new FuncionarioService();
  private static ArrayList<Funcionario> items;
  private static ObservableList<FuncionarioTable> tableItems;
  private static FuncionarioTable itemSelecionado;
  
  public TableView<FuncionarioTable> tabelaConteudo = new TableView<>();
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    TableColumn<FuncionarioTable, String> nomeCol = new TableColumn<>("Nome");
    nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));
  
    TableColumn<FuncionarioTable, String> cpfCol = new TableColumn<>("CPF");
    cpfCol.setCellValueFactory(new PropertyValueFactory("cpf"));
  
    TableColumn<FuncionarioTable, String> emailCol = new TableColumn<>("E-Mail");
    emailCol.setCellValueFactory(new PropertyValueFactory("email"));
  
    TableColumn<FuncionarioTable, String> telefoneCol = new TableColumn<>("Telefone");
    telefoneCol.setCellValueFactory(new PropertyValueFactory("telefone"));
  
    TableColumn<FuncionarioTable, String> cargoColumn = new TableColumn<>("Cargo");
    cargoColumn.setCellValueFactory(new PropertyValueFactory("cargo"));
  
    TableColumn<FuncionarioTable, Double> salarioColumn = new TableColumn<>("Salário");
    salarioColumn.setCellValueFactory(new PropertyValueFactory("salario"));
  
    TableColumn<FuncionarioTable, String> enderecoColumn = new TableColumn<>("Endereço");
    enderecoColumn.setCellValueFactory(new PropertyValueFactory("endereco"));
  
    TableColumn<FuncionarioTable, Date> dataAdmissaoColumn = new TableColumn<>("Data de Admissão");
    dataAdmissaoColumn.setCellValueFactory(new PropertyValueFactory("dataAdmissao"));
  
    TableColumn<FuncionarioTable, Date> dataDemissaoColumn = new TableColumn<>("Data de Demissão");
    dataDemissaoColumn.setCellValueFactory(new PropertyValueFactory("dataDemissao"));
  
    this.tabelaConteudo.getColumns().setAll(nomeCol, cpfCol, emailCol, telefoneCol, cargoColumn,
        salarioColumn, enderecoColumn, dataAdmissaoColumn, dataDemissaoColumn);
  
    // get data from db
    tableItems = this.listaDeItems(this.service.getAll());
    this.tabelaConteudo.setItems(tableItems);
  
    // setando configurações de seleção
    TableView.TableViewSelectionModel<FuncionarioTable> selectionModel = this.tabelaConteudo.getSelectionModel();
    selectionModel.setSelectionMode(SelectionMode.SINGLE);
  
    // escutar mudança nos selecionados
    ObservableList<FuncionarioTable> selectedItems = selectionModel.getSelectedItems();
    selectedItems.addListener((ListChangeListener<FuncionarioTable>) change -> {
      // atualizar o selecionado
      if (change.getList().size() > 0) {
        itemSelecionado = change.getList().get(0);
      }
    });
  }
  
  public ObservableList<FuncionarioTable> listaDeItems(ArrayList<Funcionario> funcionarios) {
    items = funcionarios;
    
    ArrayList<FuncionarioTable> funcionarioTableList = new ArrayList<>();
    DateFormat                  df                   = new SimpleDateFormat("dd/MM/yyyy");
    for (Funcionario funcionario: items) {
      funcionarioTableList.add(
          new FuncionarioTable(
              funcionario.getId(),
              funcionario.getNome(),
              funcionario.getTelefone(),
              funcionario.getEmail(),
              funcionario.getCpf(),
              funcionario.getCargo(),
              funcionario.getSalario(),
              funcionario.getEndereco(),
              df.format(funcionario.getDataAdmissao()),
              df.format(funcionario.getDataDemissao())
          )
      );
    }
    return FXCollections.observableArrayList(funcionarioTableList);
  }
  
  public boolean onDelete() {
    // deletando
    Integer id = itemSelecionado.getId();
    
    try {
      service.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    } finally {
      this.reloadItems();
    }
  }
  
  public void onCreate() throws IOException  {
    CreateFuncionarioController controller = new CreateFuncionarioController();
    this.createModal("Criar novo item", controller);
  }
  
  public void onEdit() throws IOException {
    Funcionario               item       = this.service.getById(itemSelecionado.getId());
    EditFuncionarioController controller = new EditFuncionarioController(item);
    this.createModal("Editar item", controller);
  }
  
  public void onAuditory() throws IOException {
    this.createModalAuditory();
  }
  
  private void createModal(String title, Object controller) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(TableButtonsController.class.getResource("/application/modals/funcionario-modal.fxml"));
    loader.setController(controller);
    
    Parent root = loader.load();
    
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.setTitle(title);
    stage.initModality(Modality.WINDOW_MODAL);
    stage.show();
  }
  
  private void createModalAuditory () throws IOException {
    ObservableList<FuncionarioTable> acessoArray = listaDeItems(this.service.getAllAuditory());
    
    TableView table = new TableView();
    
    Stage stage = new Stage();
    Scene scene = new Scene(new Group());
    TableColumn<FuncionarioTable, String> nomeCol = new TableColumn<>("Nome");
    nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));
  
    TableColumn<FuncionarioTable, String> cpfCol = new TableColumn<>("CPF");
    cpfCol.setCellValueFactory(new PropertyValueFactory("cpf"));
  
    TableColumn<FuncionarioTable, String> emailCol = new TableColumn<>("E-Mail");
    emailCol.setCellValueFactory(new PropertyValueFactory("email"));
  
    TableColumn<FuncionarioTable, String> telefoneCol = new TableColumn<>("Telefone");
    telefoneCol.setCellValueFactory(new PropertyValueFactory("telefone"));
  
    TableColumn<FuncionarioTable, String> cargoColumn = new TableColumn<>("Cargo");
    cargoColumn.setCellValueFactory(new PropertyValueFactory("cargo"));
  
    TableColumn<FuncionarioTable, Double> salarioColumn = new TableColumn<>("Salário");
    salarioColumn.setCellValueFactory(new PropertyValueFactory("salario"));
  
    TableColumn<FuncionarioTable, String> enderecoColumn = new TableColumn<>("Endereço");
    enderecoColumn.setCellValueFactory(new PropertyValueFactory("endereço"));
  
    TableColumn<FuncionarioTable, Date> dataAdmissaoColumn = new TableColumn<>("Data de Admissão");
    dataAdmissaoColumn.setCellValueFactory(new PropertyValueFactory("data admissao"));
  
    TableColumn<FuncionarioTable, Date> dataDemissaoColumn = new TableColumn<>("Data de Demissão");
    dataDemissaoColumn.setCellValueFactory(new PropertyValueFactory("data demissao"));
  
    table.getColumns().addAll(nomeCol, cpfCol, emailCol, telefoneCol, cargoColumn, salarioColumn,
        enderecoColumn, dataAdmissaoColumn, dataDemissaoColumn);
    table.setItems(acessoArray);
    ((Group) scene.getRoot()).getChildren().addAll(table);
    
    stage.setScene(scene);
    stage.setTitle("Auditoria");
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
