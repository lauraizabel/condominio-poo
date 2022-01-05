package controllers.modals;

import business.FuncionarioService;
import controllers.views.FuncionarioController;
import dados.Funcionario;
import java.sql.Date;
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
  TextField dataAdmissaoValue;
  
  @FXML
  TextField dataDemissaoValue;
  
  @FXML
  private Button submitButton;
  
  @FXML
  public void handleSubmit(ActionEvent e) {
    // Criando novo produto
    
    Funcionario item = new Funcionario(
        String.valueOf(nomeValue),
        String.valueOf(telefoneValue),
        String.valueOf(emailValue),
        String.valueOf(cpfValue),
        String.valueOf(cargoValue),
        Double.valueOf(salarioValue.getText()),
        String.valueOf(enderecoValue),
        Date.valueOf(dataAdmissaoValue.getText()),
        Date.valueOf(dataDemissaoValue.getText())
    );
    
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
