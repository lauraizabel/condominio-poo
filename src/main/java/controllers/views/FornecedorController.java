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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FornecedorController implements Initializable {
    FornecedorService service = new FornecedorService();
    private static ArrayList<Fornecedor> items;
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
        this.tabelaConteudo.setItems(this.listaDeItems());

        // setando configurações de seleção
        TableView.TableViewSelectionModel<FornecedorTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<FornecedorTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<FornecedorTable>() {
            @Override
            public void onChanged(Change<? extends FornecedorTable> change) {
                // atualizar o selecionado
                itemSelecionado = change.getList().get(0);
            }
        });
    }

    public ObservableList<FornecedorTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<FornecedorTable> fornecedorTableList = new ArrayList<FornecedorTable>();
        for ( Fornecedor fornecedor: this.items) {
            fornecedorTableList.add(
                new FornecedorTable(
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
        String itemCode = itemSelecionado.getCnpj();
        Fornecedor item = this.findItemByCnpj(itemCode);

        if ( item != null ) {
            Integer itemId = item.getId();
            System.out.println("Found: " + itemId);
//            service.deleteById(itemId);
        } else {
            System.out.println("Not found.");
        }

        return true;
    }

    public void onCreate() throws IOException  {
        CreateFornecedorController controller = new CreateFornecedorController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Fornecedor item = this.findItemByCnpj(itemSelecionado.getCnpj());
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

    private Fornecedor findItemByCnpj(String code) {
        try {
            for ( Fornecedor item : this.items ) {
                if ( item.getCnpj() == code ) {
                    return item;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO [DELETE]: " + e);
        }
        return null;
    }
}
