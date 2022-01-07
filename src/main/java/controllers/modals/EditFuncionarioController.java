package controllers.modals;

import business.FuncionarioService;
import controllers.views.FuncionarioController;
import dados.Funcionario;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
  DatePicker dataAdmissaoValue;
  
  @FXML
  DatePicker dataDemissaoValue;
  
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

    this.dataAdmissaoValue.setValue(itemSelected.getDataAdmissao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    this.dataDemissaoValue.setValue(itemSelected.getDataDemissao().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
  }
  
  @FXML
  public void handleSubmit(ActionEvent e) {
    // alterando item usando campos
    Instant instantAdimissao = Instant.from(dataAdmissaoValue.getValue().atStartOfDay(ZoneId.of("GMT-3")));
    itemSelected.setDataAdmissao(java.util.Date.from(instantAdimissao));

    Instant instantDemissao = Instant.from(dataDemissaoValue.getValue().atStartOfDay(ZoneId.of("GMT-3")));
    itemSelected.setDataDemissao(java.util.Date.from(instantDemissao));

    itemSelected.setNome(String.valueOf(nomeValue.getText()));
    itemSelected.setTelefone(String.valueOf(telefoneValue.getText()));
    itemSelected.setEmail(String.valueOf(emailValue.getText()));
    itemSelected.setCpf(String.valueOf(cpfValue.getText()));
    itemSelected.setCargo(String.valueOf(cargoValue.getText()));
    itemSelected.setSalario(Double.valueOf(salarioValue.getText()));
    itemSelected.setEndereco(String.valueOf(enderecoValue.getText()));
    
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
