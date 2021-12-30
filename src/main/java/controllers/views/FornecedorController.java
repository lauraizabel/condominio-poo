package controllers.views;

import business.FornecedorService;
import controllers.TableButtonsController;
import controllers.modals.CreateFornecedorController;
import controllers.modals.EditFornecedorController;
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
import tables.FornecedorTable;
import tables.ProdutoTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FornecedorController implements Initializable {
    FornecedorService service = new FornecedorService();
    private static ArrayList<Fornecedor> items;
    private static ObservableList<FornecedorTable> tableItems;
    private static FornecedorTable itemSelecionado;

    @FXML
    public TableView<FornecedorTable> tabelaConteudo = new TableView<FornecedorTable>();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns

        TableColumn<FornecedorTable, String> nomeCol = new TableColumn<FornecedorTable, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));

        TableColumn<FornecedorTable, String> cnpjCol = new TableColumn<FornecedorTable, String>("CNPJ");
        cnpjCol.setCellValueFactory(new PropertyValueFactory("cnpj"));

        TableColumn<FornecedorTable, String> enderecoCol = new TableColumn<FornecedorTable, String>("Endereco");
        enderecoCol.setCellValueFactory(new PropertyValueFactory("endereco"));

        TableColumn<FornecedorTable, String> telefoneCol = new TableColumn<FornecedorTable, String>("Telefone");
        telefoneCol.setCellValueFactory(new PropertyValueFactory("telefone"));

        TableColumn<FornecedorTable, String> emailCol = new TableColumn<FornecedorTable, String>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(nomeCol, cnpjCol, enderecoCol, telefoneCol, emailCol);

        // get data from db
        tableItems = this.listaDeItems();
        this.tabelaConteudo.setItems(tableItems);

        // setando configurações de seleção
        TableView.TableViewSelectionModel<FornecedorTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<FornecedorTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<FornecedorTable>() {
            @Override
            public void onChanged(Change<? extends FornecedorTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<FornecedorTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<FornecedorTable> fornecedorTableList = new ArrayList<FornecedorTable>();
        for ( Fornecedor fornecedor: this.items) {
            fornecedorTableList.add(
                new FornecedorTable(
                    fornecedor.getId(),
                    fornecedor.getNome(),
                    fornecedor.getCnpj(),
                    fornecedor.getEndereco(),
                    fornecedor.getTelefone(),
                    fornecedor.getEmail()
                )
            );
        }
        return FXCollections.observableArrayList(fornecedorTableList);
    }

    public boolean onDelete() {
        // deletando
        Integer itemId = itemSelecionado.getId();
        try {
            service.deleteById(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.reloadItems();
        }
        return true;
    }

    public void onCreate() throws IOException  {
        CreateFornecedorController controller = new CreateFornecedorController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Fornecedor item = this.service.getById(itemSelecionado.getId());
        EditFornecedorController controller = new EditFornecedorController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/fornecedor-modal.fxml"));
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
        for (FornecedorTable item: this.listaDeItems()) {
            tableItems.add(item);
        }
    }
}
