package controllers.views;

import business.ServicoService;
import controllers.TableButtonsController;
import controllers.modals.CreateServicoController;
import controllers.modals.EditServicoController;
import dados.Servico;
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
import tables.ServicoTable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServicoController implements Initializable {
    ServicoService service = new ServicoService();
    private static ArrayList<Servico> items;
    private static ServicoTable itemSelecionado;

    @FXML
    public TableView<ServicoTable> tabelaConteudo = new TableView<ServicoTable>();;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // create columns
        TableColumn<ServicoTable, String> codigoCol = new TableColumn<ServicoTable, String>("Código");
        codigoCol.setCellValueFactory(new PropertyValueFactory("codigo"));

        TableColumn<ServicoTable, String> descricaoCol = new TableColumn<ServicoTable, String>("Descrição");
        descricaoCol.setCellValueFactory(new PropertyValueFactory("descricao"));

        TableColumn<ServicoTable, Double> valorCol = new TableColumn<ServicoTable, Double>("Valor");
        valorCol.setCellValueFactory(new PropertyValueFactory("valor"));

        TableColumn<ServicoTable, String> fornecedorCol = new TableColumn<ServicoTable, String>("Fornecedor");
        fornecedorCol.setCellValueFactory(new PropertyValueFactory("fornecedor"));

        TableColumn<ServicoTable, String> requerenteCol = new TableColumn<ServicoTable, String>("Requerente");
        requerenteCol.setCellValueFactory(new PropertyValueFactory("requerente"));

        // add columns
        this.tabelaConteudo.getColumns().setAll(codigoCol, descricaoCol, valorCol, fornecedorCol,requerenteCol);

        // get data from db
        this.tabelaConteudo.setItems(this.listaDeItems());

        // setando configurações de seleção
        TableView.TableViewSelectionModel<ServicoTable> selectionModel = this.tabelaConteudo.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        // escutar mudança nos selecionados
        ObservableList<ServicoTable> selectedItems = selectionModel.getSelectedItems();
        selectedItems.addListener(new ListChangeListener<ServicoTable>() {
            @Override
            public void onChanged(Change<? extends ServicoTable> change) {
                // atualizar o selecionado
                itemSelecionado = change.getList().get(0);
            }
        });
    }

    public ObservableList<ServicoTable> listaDeItems() {
        this.items = this.service.getAll();
        ArrayList<ServicoTable> itemTableList = new ArrayList<ServicoTable>();
        for ( Servico item: this.items) {
            itemTableList.add(
                new ServicoTable(
                    item.getCodigo(),
                    item.getDescricao(),
                    item.getValor(),
                    item.getFornecedor().getNome(),
                    item.getRequerente().getNome()
                )
            );
        }
        return FXCollections.observableArrayList(itemTableList);
    }

    public boolean onDelete() {
        // deletando
        String itemCode = itemSelecionado.getCodigo();
        Servico item = this.findItemByCode(itemCode);

        if ( item != null ) {
            Integer itemId = item.getId();
            service.deleteById(itemId);
        }

        return true;
    }

    public void onCreate() throws IOException  {
        CreateServicoController controller = new CreateServicoController();
        this.createModal("Criar novo item", controller);
    }

    public void onEdit() throws IOException  {
        Servico item = this.findItemByCode(itemSelecionado.getCodigo());
        EditServicoController controller = new EditServicoController(item);
        this.createModal("Editar item", controller);
    }

    private void createModal(String title, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(TableButtonsController.class.getResource("/application/modals/servico-modal.fxml"));
        loader.setController(controller);

        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    private Servico findItemByCode(String code) {
        try {
            for ( Servico item : this.items ) {
                if ( item.getCodigo() == code ) {
                    return item;
                }
            }
        } catch (Exception e) {
            System.out.println("ERRO [DELETE]: " + e);
        }
        return null;
    }

    public void reloadItems() {
        // How to reload ?
//        this.tabelaConteudo.setItems(this.listaDeServicos());  -> does not work
    }

}
