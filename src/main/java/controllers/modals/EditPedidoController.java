package controllers.modals;

import business.FuncionarioService;
import business.PedidoService;
import business.ProdutoService;
import controllers.views.ProdutoController;
import dados.Funcionario;
import dados.Pedido;
import dados.PedidoDeCompra;
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

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditPedidoController implements Initializable {
    PedidoService service = new PedidoService();
    ProdutoService produtoService = new ProdutoService();
    FuncionarioService funcionarioService = new FuncionarioService();

    static ArrayList<Produto> itemsProduto;
    static ArrayList<Funcionario> itemsFuncionario;
    static Pedido itemSelected;
    static ObservableList<String> selectedFeatures;

    @FXML
    private ComboBox<String> funcionarioValues;

    @FXML
    CheckComboBox<String> produtoValues;

    @FXML
    private Button submitButton;

    public EditPedidoController(Pedido itemSelected) {
        this.itemSelected = itemSelected;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.funcionarioValues.setItems(FXCollections.observableArrayList(getFuncionarios()));
        produtoValues.getItems().addAll(getProdutos());

        Integer funcionarioIndex = getFuncionarioIndex(itemSelected.getRequerente());
        if ( funcionarioIndex >= 0 ) {
            this.funcionarioValues.getSelectionModel().select(funcionarioIndex);
        }

        ArrayList<Integer> produtoIndexes = getProdutosIndex(itemSelected.getProdutos());
        for (Integer i: produtoIndexes) {
            this.produtoValues.getCheckModel().check(i);
        }

        produtoValues.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            public void onChanged(Change<? extends String> c) {
                selectedFeatures = produtoValues.getCheckModel().getCheckedItems();
            }
        });
    }

    private Integer getFuncionarioIndex(Funcionario funcionario) {
        int i;
        for ( i = 0; i < this.funcionarioValues.getItems().size(); i++ ) {
            if ( this.funcionarioValues.getItems().get(i).equals(funcionario.getNome()) ) {
                return i;
            }
        }
        return -1;
    }

    private ArrayList<Integer> getProdutosIndex(List<Produto> produtos) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for ( int i = 0; i < this.produtoValues.getItems().size(); i++ ) {
            for (Produto produto: produtos) {
                if ( this.produtoValues.getItems().get(i).equals(produto.getNome()) ) {
                    indexes.add(i);
                }
            }
        }
        return indexes;
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

        itemSelected.setProdutos(produtos);
        itemSelected.setRequerente(itemsFuncionario.get(funcionarioIdx));

        service.update(itemSelected);

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        ProdutoController controller = new ProdutoController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
