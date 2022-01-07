package controllers.modals;

import business.FuncionarioService;
import controllers.views.FuncionarioController;
import dados.Funcionario;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateFuncionarioController {
  FuncionarioService service = new FuncionarioService();
  
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
  
  @FXML
  public void handleSubmit(ActionEvent e) {
    // Criando novo produto

    Instant instantAdmissao = Instant.from(dataAdmissaoValue.getValue().atStartOfDay(ZoneId.of("GMT-3")));
    Instant instantDemissao = Instant.from(dataDemissaoValue.getValue().atStartOfDay(ZoneId.of("GMT-3")));
    
    Funcionario item = new Funcionario(
        String.valueOf(nomeValue.getText()),
        String.valueOf(telefoneValue.getText()),
        String.valueOf(emailValue.getText()),
        String.valueOf(cpfValue.getText()),
        String.valueOf(cargoValue.getText()),
        Double.valueOf(salarioValue.getText()),
        String.valueOf(enderecoValue.getText()),
        java.util.Date.from(instantAdmissao),
        java.util.Date.from(instantDemissao)
    );

    System.out.println(item.getNome());
    System.out.println(item.getDataAdmissao());

    try {
      service.save(item);
    } catch (Exception error) {
      error.printStackTrace();
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
