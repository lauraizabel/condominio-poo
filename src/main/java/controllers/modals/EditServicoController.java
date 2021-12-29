package controllers.modals;

import business.FornecedorService;
import business.FuncionarioService;
import business.ServicoService;
import controllers.views.ServicoController;
import dados.Fornecedor;
import dados.Funcionario;
import dados.Servico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditServicoController implements Initializable {
    FornecedorService fornecedorService = new FornecedorService();
    FuncionarioService funcionarioService = new FuncionarioService();
    ServicoService servicoService = new ServicoService();

    static ArrayList<Fornecedor> fornecedores;
    static ArrayList<Funcionario> funcionarios;
    static Servico itemSelected;

    @FXML
    private TextField codigoValue;

    @FXML
    private TextField descricaoValue;

    @FXML
    private TextField valorValue;

    @FXML
    private ComboBox<String> fornecedoresValues;

    @FXML
    private ComboBox<String> funcionariosValues;


    public EditServicoController(Servico itemSelected) {
        this.itemSelected = itemSelected;
    }

    @FXML
    private Button submitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.fornecedoresValues.setItems(FXCollections.observableArrayList(getFornecedores()));
        this.funcionariosValues.setItems(FXCollections.observableArrayList(getFuncionarios()));


        // setting existing values
        this.codigoValue.setText(itemSelected.getCodigo());
        this.descricaoValue.setText(itemSelected.getDescricao());
        this.valorValue.setText(itemSelected.getValor().toString());

        Integer fornecedorIndex = getFornecedorIndex(itemSelected.getFornecedor());
        if ( fornecedorIndex >= 0 ) {
            this.fornecedoresValues.getSelectionModel().select(fornecedorIndex);
        }

        Integer requerenteIndex = getRequerenteIndex(itemSelected.getRequerente());
        if( requerenteIndex >= 0 ) {
            this.funcionariosValues.getSelectionModel().select(requerenteIndex);
        }
    }

    private Integer getFornecedorIndex(Fornecedor fornecedor) {
        int i;
        for ( i = 0; i < this.fornecedoresValues.getItems().size(); i++ ) {
            if ( this.fornecedoresValues.getItems().get(i).equals(fornecedor.getNome()) ) {
                return i;
            }
        }
        return -1;
    }

    private Integer getRequerenteIndex(Funcionario funcionario) {
        int i;
        for ( i = 0; i < this.funcionariosValues.getItems().size(); i++ ) {
            if ( this.funcionariosValues.getItems().get(i).equals(funcionario.getNome()) ) {
                return i;
            }
        }
        return -1;
    }

    private ObservableList<String> getFornecedores() {
        fornecedores = this.fornecedorService.getAll();

        ArrayList<String> fornecedoresList = new ArrayList<String>();
        for ( Fornecedor fornecedor: fornecedores) {
            fornecedoresList.add(fornecedor.getNome());
        }

        return FXCollections.observableArrayList(fornecedoresList);
    }

    private ObservableList<String> getFuncionarios() {
        this.funcionarios = this.funcionarioService.getAll();

        ArrayList<String> funcionariosList = new ArrayList<String>();
        for ( Funcionario funcinario: this.funcionarios) {
            funcionariosList.add(funcinario.getNome());
        }

        return FXCollections.observableArrayList(funcionariosList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        Integer quantidade = 0;

        // pegando campos do fxml
        itemSelected.setCodigo(String.valueOf(codigoValue.getText()));
        itemSelected.setDescricao(descricaoValue.getText());
        itemSelected.setValor(Double.valueOf(valorValue.getText()));
        int fornecedorIdx = fornecedoresValues.getSelectionModel().getSelectedIndex();
        itemSelected.SetFornecedor(fornecedores.get(fornecedorIdx));
        int requerenteIdx = funcionariosValues.getSelectionModel().getSelectedIndex();
        itemSelected.setRequerente(funcionarios.get(requerenteIdx));


        // Criando novo produto;
        servicoService.update(itemSelected);

        this.finish();
    };

    private void finish() {
        // atualiza conteúdo
        ServicoController controller = new ServicoController();
        controller.reloadItems();

        // fecha janela
        Stage stage = (Stage) submitButton.getScene().getWindow();
        stage.close();
    }
}
