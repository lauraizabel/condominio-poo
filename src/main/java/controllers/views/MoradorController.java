package controllers.views;

import business.MoradorService;
import controllers.TableButtonsController;
import controllers.modals.CreateMoradorController;
import controllers.modals.EditMoradorController;
import dados.Morador;
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
import tables.MoradorTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MoradorController implements Initializable {
    MoradorService service = new MoradorService();
    private static ArrayList<Morador> items;
    private static ObservableList<MoradorTable> tableItems;
    private static MoradorTable itemSelecionado;

    @FXML
    public TableView<MoradorTable> tabelaConteudo = new TableView<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // create columns

        TableColumn<MoradorTable, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));

        TableColumn<MoradorTable, String> telefoneCol = new TableColumn<>("Telefone");
        telefoneCol.setCellValueFactory(new PropertyValueFactory("telefone"));

        TableColumn<MoradorTable, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn<MoradorTable, String> cpfCol = new TableColumn<>("CPF");
        cpfCol.setCellValueFactory(new PropertyValueFactory("cpf"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(nomeCol, telefoneCol, emailCol, cpfCol);

        // get data from db
        tableItems = this.listaDeItems(this.service.getAll());
        this.tabelaConteudo.setItems(tableItems);

        // setando configurações de seleção
        TableView.TableViewSelectionModel<MoradorTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<MoradorTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<MoradorTable>() {
            @Override
            public void onChanged(Change<? extends MoradorTable> change) {
                // atualizar o selecionado
                if ( change.getList().size() > 0 ) {
                    itemSelecionado = change.getList().get(0);
                }
            }
        });
    }

    public ObservableList<MoradorTable> listaDeItems(ArrayList<Morador> moradorArrayList) {
        this.items = moradorArrayList;
        ArrayList<MoradorTable> moradorTableList = new ArrayList<MoradorTable>();
        for ( Morador morador: this.items) {
            moradorTableList.add(
                    new MoradorTable(
                            morador.getId(),
                            morador.getNome(),
                            morador.getTelefone(),
                            morador.getEmail(),
                            morador.getCpf()
                    )
            );
        }
        return FXCollections.observableArrayList(moradorTableList);
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
        CreateMoradorController controller = new CreateMoradorController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Morador item = this.service.getById(itemSelecionado.getId());
        EditMoradorController controller = new EditMoradorController(item);
        this.createModal("Editar item", controller);
    }

    public void onAuditory() throws IOException {
        this.createModalAuditory();
    }

    private void createModalAuditory () throws IOException {
        ObservableList<MoradorTable> morador = listaDeItems(this.service.getAllAuditory());

        TableView table = new TableView();

        Stage stage = new Stage();
        Scene scene = new Scene(new Group());

        TableColumn<MoradorTable, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory("nome"));

        TableColumn<MoradorTable, String> telefoneCol = new TableColumn<>("Telefone");
        telefoneCol.setCellValueFactory(new PropertyValueFactory("telefone"));

        TableColumn<MoradorTable, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn<MoradorTable, String> cpfCol = new TableColumn<>("CPF");
        cpfCol.setCellValueFactory(new PropertyValueFactory("cpf"));

        table.getColumns().setAll(nomeCol, telefoneCol, emailCol, cpfCol);
        table.setItems(morador);
        ((Group) scene.getRoot()).getChildren().addAll(table);

        stage.setScene(scene);
        stage.setTitle("Auditoria");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }



    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/morador-modal.fxml"));
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
        for (MoradorTable item: this.listaDeItems(this.service.getAll())) {
            tableItems.add(item);
        }
    }

}
