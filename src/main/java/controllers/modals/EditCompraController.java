package controllers.modals;

import business.AlmoxarifadoService;
import business.CompraService;
import business.FuncionarioService;
import business.ProdutoService;
import controllers.views.AlmoxarifadoController;
import controllers.views.CompraController;
import controllers.views.ProdutoController;
import dados.Almoxarifado;
import dados.Compra;
import dados.Funcionario;
import dados.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EditCompraController implements Initializable {
    CompraService service = new CompraService();
    FuncionarioService funcionarioService = new FuncionarioService();
    ProdutoService produtoService = new ProdutoService();
    Compra itemSelected;

    static ArrayList<Funcionario> funcionarios;
    static ArrayList<Produto> produtos;

    @FXML
    ComboBox<String> funcionarioValues;

    @FXML
    ComboBox<String> produtoValues;

    @FXML
    TextField quantidadeValue;

    @FXML
    TextField valorUnitarioValue;

    @FXML
    DatePicker dataValue;

    @FXML
    private Button submitButton;

    public EditCompraController(Compra itemSelected) {
        this.itemSelected = itemSelected;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        funcionarioValues.setItems(FXCollections.observableArrayList(getFuncionarios()));
        produtoValues.setItems(FXCollections.observableArrayList(getProdutos()));

        this.dataValue.setValue(itemSelected.getDataAlteracao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.quantidadeValue.setText(itemSelected.getQuantidade().toString());
        this.valorUnitarioValue.setText(itemSelected.getValorUnitario().toString());

        Integer funcionarioIndex = getFuncionarioIndex(itemSelected.getFuncionario());
        if ( funcionarioIndex >= 0 ) {
            this.funcionarioValues.getSelectionModel().select(funcionarioIndex);
        }

        Integer produtoIndex = getProdutoIndex(itemSelected.getProduto());
        if ( produtoIndex >= 0 ) {
            this.produtoValues.getSelectionModel().select(produtoIndex);
        }
    }

    private Integer getFuncionarioIndex(Funcionario funcionario) {
        for ( int i = 0; i < this.funcionarioValues.getItems().size(); i++ ) {
            if ( this.funcionarioValues.getItems().get(i).equals(funcionario.getNome()) ) {
                return i;
            }
        }
        return -1;
    }

    private Integer getProdutoIndex(Produto produto) {
        for ( int i = 0; i < this.produtoValues.getItems().size(); i++ ) {
            if ( this.produtoValues.getItems().get(i).equals(produto.getNome()) ) {
                return i;
            }
        }
        return -1;
    }

    private ObservableList<String> getFuncionarios() {
        funcionarios = this.funcionarioService.getAll();

        ArrayList<String> funcionarioList = new ArrayList<String>();
        for ( Funcionario funcionario: funcionarios) {
            funcionarioList.add(funcionario.getNome());
        }

        return FXCollections.observableArrayList(funcionarioList);
    }

    private ObservableList<String> getProdutos() {
        produtos = this.produtoService.getAll();

        ArrayList<String> produtoList = new ArrayList<String>();
        for ( Produto produto: produtos) {
            produtoList.add(produto.getNome());
        }

        return FXCollections.observableArrayList(produtoList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        itemSelected.setQuantidade(Integer.valueOf(quantidadeValue.getText()));
        itemSelected.setValorUnitario(Double.valueOf(valorUnitarioValue.getText()));

        Instant instant = Instant.from(dataValue.getValue().atStartOfDay(ZoneId.of("GMT-3")));
        itemSelected.setDataAlteracao(Date.from(instant));

        int produtoIdx = produtoValues.getSelectionModel().getSelectedIndex();
        itemSelected.setProduto(produtos.get(produtoIdx));

        int funcionarioIdx = funcionarioValues.getSelectionModel().getSelectedIndex();
        itemSelected.setFuncionario(funcionarios.get(funcionarioIdx));

        // Criando novo produto;
        service.update(itemSelected);

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        CompraController controller = new CompraController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }

}
