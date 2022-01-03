package controllers.modals;

import business.FuncionarioService;
import business.PedidoService;
import business.ProdutoService;
import controllers.views.PedidoController;
import controllers.views.ProdutoController;
import dados.Funcionario;
import dados.Pedido;
import dados.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatePedidoController implements Initializable {
    PedidoService service = new PedidoService();
    ProdutoService produtoService = new ProdutoService();
    FuncionarioService funcionarioService = new FuncionarioService();

    static ArrayList<Produto> itemsProduto;
    static ArrayList<Funcionario> itemsFuncionario;
    static ObservableList<String> selectedFeatures;

    @FXML
    private ComboBox<String> funcionarioValues;

    @FXML
    CheckComboBox<String> produtoValues;

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.funcionarioValues.setItems(FXCollections.observableArrayList(getFuncionarios()));
        produtoValues.getItems().addAll(getProdutos());

        produtoValues.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                selectedFeatures = produtoValues.getCheckModel().getCheckedItems();
            }
        });
    }

    private ObservableList<String> getProdutos() {
        itemsProduto = this.produtoService.getAll();

        ArrayList<String> itemsList = new ArrayList<String>();
        for ( Produto item: itemsProduto) {
            itemsList.add(item.getNome());
        }
        return FXCollections.observableList(itemsList);
    }

    private ObservableList<String> getFuncionarios() {
        itemsFuncionario = this.funcionarioService.getAll();

        ArrayList<String> itemsList = new ArrayList<String>();
        for ( Funcionario item: itemsFuncionario) {
            itemsList.add(item.getNome());
        }
        return FXCollections.observableArrayList(itemsList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        Integer funcionarioIdx = funcionarioValues.getSelectionModel().getSelectedIndex();
        ObservableList<Integer> produtosIdx = produtoValues.getCheckModel().getCheckedIndices();
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        for ( Integer produtoIdx: produtosIdx ) {
            produtos.add(itemsProduto.get(produtoIdx));
        }

        Pedido pedido = new Pedido(
            itemsFuncionario.get(funcionarioIdx),
            produtos
        );
        service.save(pedido);

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        PedidoController controller = new PedidoController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
