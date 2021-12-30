package controllers.modals;

import business.CompraService;
import business.FuncionarioService;
import business.ProdutoService;
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

public class CreateCompraController implements Initializable {
    CompraService service = new CompraService();
    FuncionarioService funcionarioService = new FuncionarioService();
    ProdutoService produtoService = new ProdutoService();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        funcionarioValues.setItems(FXCollections.observableArrayList(getFuncionarios()));
        produtoValues.setItems(FXCollections.observableArrayList(getProdutos()));
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
        Integer funcionarioIdx = Integer.valueOf(funcionarioValues.getSelectionModel().getSelectedIndex());
        Integer produtoIdx = Integer.valueOf(produtoValues.getSelectionModel().getSelectedIndex());

        Instant instant = Instant.from(dataValue.getValue().atStartOfDay(ZoneId.of("GMT-3")));

        // Criando novo produto
        Compra item = new Compra(
            produtos.get(produtoIdx),
            Integer.valueOf(quantidadeValue.getText()),
            Date.from(instant),
            funcionarios.get(funcionarioIdx),
            Double.valueOf(valorUnitarioValue.getText())
        );

        try {
            service.save(item);
        } catch (Exception error) {
            error.printStackTrace();
        }

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
//        AlmoxarifadoController controller = new AlmoxarifadoController();
//        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }


}
