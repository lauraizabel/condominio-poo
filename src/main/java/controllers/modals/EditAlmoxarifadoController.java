package controllers.modals;

import business.AlmoxarifadoService;
import business.FuncionarioService;
import business.ProdutoService;
import controllers.views.ProdutoController;
import dados.Almoxarifado;
import dados.Fornecedor;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class EditAlmoxarifadoController implements Initializable {

    AlmoxarifadoService service = new AlmoxarifadoService();
    ProdutoService produtoService = new ProdutoService();
    FuncionarioService funcionarioService = new FuncionarioService();

    static ArrayList<Funcionario> funcionarios;
    static ArrayList<Produto> produtos;

    static Almoxarifado itemSelected;

    @FXML
    ComboBox<String> funcionarioValues;

    @FXML
    ComboBox<String> produtoValues;

    @FXML
    TextField quantidadeAdcionadaValue;

    @FXML
    TextField quantidadeRemovidaValue;

    @FXML
    DatePicker dataValue;

    public EditAlmoxarifadoController(Almoxarifado itemSelected) {
        this.itemSelected = itemSelected;
    }

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.funcionarioValues.setItems(FXCollections.observableArrayList(getFuncionarios()));
        this.produtoValues.setItems(FXCollections.observableArrayList(getProdutos()));

        // setting existing values
        this.dataValue.setValue(itemSelected.getDataAlteracao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        this.quantidadeRemovidaValue.setText(itemSelected.getQuantidadeRemovida().toString());
        this.quantidadeAdcionadaValue.setText(itemSelected.getQuantidadeAdicionada().toString());

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

        ArrayList<String> funcionariosList = new ArrayList<String>();
        for ( Funcionario funcionario: funcionarios) {
            funcionariosList.add(funcionario.getNome());
        }
        return FXCollections.observableArrayList(funcionariosList);
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
        // pegando campos do fxml
        itemSelected.setQuantidadeAdicionada(Integer.valueOf(quantidadeAdcionadaValue.getText()));
        itemSelected.setQuantidadeRemovida(Integer.valueOf(quantidadeRemovidaValue.getText()));

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
        ProdutoController controller = new ProdutoController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
