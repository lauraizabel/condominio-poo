package controllers.modals;

import business.FuncionarioService;
import controllers.views.FuncionarioController;
import dados.Funcionario;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditFuncionarioController implements Initializable {
  FuncionarioService service = new FuncionarioService();
  private static Funcionario itemSelected;
  
  @FXML
  TextField nomeValue;
  
  @FXML
  TextField cpfValue;
  
  @FXML
  TextField emailValue;
  
  @FXML
  TextField telefoneValue;
  
  @FXML
  TextField cargoValue;
  
  @FXML
  TextField salarioValue;
  
  @FXML
  TextField enderecoValue;
  
  @FXML
  TextField dataAdmissaoValue;
  
  @FXML
  TextField dataDemissaoValue;
  
  @FXML
  private Button submitButton;
  
  public EditFuncionarioController(Funcionario item) {
    this.itemSelected = item;
  }
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.nomeValue.setText(itemSelected.getNome());
    this.telefoneValue.setText(itemSelected.getTelefone());
    this.emailValue.setText(itemSelected.getEmail());
    this.cpfValue.setText(itemSelected.getCpf());
    this.cargoValue.setText(itemSelected.getCargo());
    this.salarioValue.setText(itemSelected.getSalario().toString());
    this.enderecoValue.setText(itemSelected.getEndereco());
    this.dataAdmissaoValue.setText(itemSelected.getDataAdmissao().toString());
    this.dataDemissaoValue.setText(itemSelected.getDataDemissao().toString());
  }
  
  @FXML
  public void handleSubmit(ActionEvent e) {
    // alterando item usando campos
    itemSelected.setNome(String.valueOf(nomeValue.getText()));
    itemSelected.setTelefone(String.valueOf(telefoneValue.getText()));
    itemSelected.setEmail(String.valueOf(emailValue.getText()));
    itemSelected.setCpf(String.valueOf(cpfValue.getText()));
    itemSelected.setCargo(String.valueOf(cargoValue));
    itemSelected.setSalario(Double.valueOf(salarioValue.getText()));
    itemSelected.setEndereco(String.valueOf(enderecoValue));
    itemSelected.setDataAdmissao(Date.valueOf(dataAdmissaoValue.getText()));
    itemSelected.setDataDemissao(Date.valueOf(dataDemissaoValue.getText()));
    
    // Atualizando item;
    try {
      service.update(itemSelected);
    } catch (Exception error) {
      Alert alert = new Alert(Alert.AlertType.WARNING, error.getMessage(), ButtonType.CLOSE);
      alert.show();
    }
    this.finish();
  }
  
  private void finish() {
    // atualiza conteúdo
    FuncionarioController controller = new FuncionarioController();
    controller.reloadItems();
    
    // fecha janela
    Stage stage = (Stage) submitButton.getScene().getWindow();
    stage.close();
  }
}
