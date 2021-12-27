package controllers.modals;

import business.FornecedorService;
import business.FuncionarioService;
import business.ServicoService;
import dados.Fornecedor;
import dados.Funcionario;
import dados.Servico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateServicoController implements Initializable {
    FornecedorService fornecedorService = new FornecedorService();
    FuncionarioService funcionarioService = new FuncionarioService();
    ServicoService service = new ServicoService();

    static ArrayList<Fornecedor>fornecedores;
    static ArrayList<Funcionario>funcionarios;

    @FXML
    TextField codigoValue;

    @FXML
    TextField descricaoValue;

    @FXML
    TextField valorValue;

    @FXML
    ComboBox<String> fornecedoresValues;

    @FXML
    ComboBox<String> funcionariosValues;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fornecedoresValues.setItems(FXCollections.observableArrayList(getFornecedores()));
        System.out.println("forn ok");
        funcionariosValues.setItems(FXCollections.observableArrayList(getFuncionarios()));
        System.out.println("func ok");
    }

    private ObservableList<String> getFornecedores() {

        this.fornecedores = this.fornecedorService.getAll();

        ArrayList<String> fornecedoresList = new ArrayList<String>();
        for ( Fornecedor fornecedor: this.fornecedores) {
            fornecedoresList.add(fornecedor.getNome());
        }
        System.out.println(fornecedoresList);
        return FXCollections.observableArrayList(fornecedoresList);
    }

    private ObservableList<String> getFuncionarios() {
        System.out.println("*");
        this.funcionarios = this.funcionarioService.getAll();

        ArrayList<String> funcionariosList = new ArrayList<String>();
        for ( Funcionario funcinario: this.funcionarios) {
            funcionariosList.add(funcinario.getNome());
            System.out.println("func" + funcinario.getNome());
        }
        System.out.println(funcionariosList);
        return FXCollections.observableArrayList(funcionariosList);
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        Integer fornecedorIdx = Integer.valueOf(fornecedoresValues.getSelectionModel().getSelectedIndex());
        Integer funcionarioIdx = Integer.valueOf(funcionariosValues.getSelectionModel().getSelectedIndex());

        // Criando novo Serviço
        Servico servico = new Servico(
                String.valueOf(descricaoValue.getText()),
                Double.valueOf(valorValue.getText()),
                String.valueOf(codigoValue.getText()),
                funcionarios.get(funcionarioIdx),
                fornecedores.get(fornecedorIdx)
        );
        service.save(servico);
    };
}
