package controllers.views;

import business.AcessoPermitidoService;
import controllers.TableButtonsController;
import controllers.modals.CreateAcessoController;
import controllers.modals.EditAcessoController;
import dados.AcessoPermitido;
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
import tables.AcessoTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AcessoPermitidoController implements Initializable {
    AcessoPermitidoService service = new AcessoPermitidoService();
    private static ArrayList<AcessoPermitido> items;
    private static ObservableList<AcessoTable> tableItems;
    private static AcessoTable itemSelecionado;

    @FXML
    public TableView<AcessoTable> tabelaConteudo = new TableView<AcessoTable>();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns

        TableColumn<AcessoTable, String> nomeCol = new TableColumn<AcessoTable, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));

        TableColumn<AcessoTable, String> cpfCol = new TableColumn<AcessoTable, String>("CPF");
        cpfCol.setCellValueFactory(new PropertyValueFactory("cpf"));

        TableColumn<AcessoTable, String> emailCol = new TableColumn<AcessoTable, String>("E-Mail");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn<AcessoTable, String> telefoneCol = new TableColumn<AcessoTable, String>("Telefone");
        telefoneCol.setCellValueFactory(new PropertyValueFactory("telefone"));

        TableColumn<AcessoTable, String> permitidoCol = new TableColumn<AcessoTable, String>("Acesso permitido");
        permitidoCol.setCellValueFactory(new PropertyValueFactory("permitido"));

        TableColumn<AcessoTable, String> apartamentoCol = new TableColumn<AcessoTable, String>("Numero do Apartamento");
        apartamentoCol.setCellValueFactory(new PropertyValueFactory("apartamento"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(cpfCol, nomeCol, emailCol, telefoneCol, permitidoCol, apartamentoCol);

        // get data from db
        tableItems = this.listaDeItems();
        this.tabelaConteudo.setItems(tableItems);

        // setando configurações de seleção
        TableView.TableViewSelectionModel<AcessoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<AcessoTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<AcessoTable>() {
            @Override
            public void onChanged(Change<? extends AcessoTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<AcessoTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<AcessoTable> itemTableList = new ArrayList<AcessoTable>();
        for ( AcessoPermitido item: this.items) {
            String permitido = item.isPermitido() ? "Sim" : "Não";
            itemTableList.add(
                new AcessoTable(
                    item.getId(),
                    item.getNome(),
                    item.getCpf(),
                    item.getEmail(),
                    item.getTelefone(),
                    item.getApartamento().getNumApartamento(),
                    permitido
                )
            );
        }
        return FXCollections.observableArrayList(itemTableList);
    }

    public boolean onDelete() {
        Integer itemId = itemSelecionado.getId();
        try {
            service.deleteById(itemId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            reloadItems();
        }
    }

    public void onCreate() throws IOException  {
        CreateAcessoController controller = new CreateAcessoController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        AcessoPermitido item = this.service.getById(itemSelecionado.getId());
        EditAcessoController controller = new EditAcessoController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/acessoPermitido-modal.fxml"));
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
        for (AcessoTable item: this.listaDeItems()) {
            tableItems.add(item);
        }
    }

}
