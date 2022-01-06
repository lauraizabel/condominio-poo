package controllers.modals;

import business.ApartamentoService;
import business.CarroServices;
import business.MoradorService;
import controllers.views.ApartamentoController;
import dados.Apartamento;
import dados.Carro;
import dados.Morador;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

public class CreateApartamentoController implements Initializable {
  ApartamentoService service = new ApartamentoService();
  MoradorService moradorService = new MoradorService();
  CarroServices carroService = new CarroServices();

  private static ArrayList<Morador> moradores;
  private static ArrayList<Carro> carros;
  private static ObservableList<String> moradorSelected;
  private static ObservableList<String> carroSelected;
  
  @FXML
  TextField blocoValue;
  
  @FXML
  TextField andarValue;
  
  @FXML
  TextField numeroValue;
  
  @FXML
  CheckComboBox<String> moradorValues;
  
  @FXML
  CheckComboBox<String> carroValues;
  
  @FXML
  private javafx.scene.control.Button submitButton;
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.carroValues.getItems().addAll(FXCollections.observableList(getCarros()));
    carroValues.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c ->
        carroSelected = carroValues.getCheckModel().getCheckedItems()
    );
    
    this.moradorValues.getItems().addAll(FXCollections.observableList(getMoradores()));
    moradorValues.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c ->
        moradorSelected = moradorValues.getCheckModel().getCheckedItems()
    );
  }
  
  private ObservableList<String> getMoradores() {
   moradores = this.moradorService.getAll();
   ArrayList<String> moradoresList = new ArrayList<>();
   moradores.forEach(morador -> moradoresList.add(morador.getNome()));
   return FXCollections.observableArrayList(moradoresList);
  }
  
  private ObservableList<String> getCarros() {
    carros = this.carroService.getAll();
    ArrayList<String> carrosList = new ArrayList<>();
    carros.forEach(carro -> carrosList.add(carro.getPlaca()));
    return FXCollections.observableArrayList(carrosList);
  }
  
  @FXML
  public void handleSubmit(ActionEvent e) {
    ObservableList<Integer> carrosIdx = carroValues.getCheckModel().getCheckedIndices();
    ObservableList<Integer> moradoresIdx = moradorValues.getCheckModel().getCheckedIndices();
    ArrayList<Carro> carroList = new ArrayList<>();
    ArrayList<Morador> moradorList = new ArrayList<>();
    
    for (Integer i: carrosIdx) {
      carroList.add(carros.get(i));
    }
    
    for (Integer i: moradoresIdx) {
      moradorList.add(moradores.get(i));
    }
  
    Apartamento apt = new Apartamento(
        String.valueOf(blocoValue.getText()),
        Integer.parseInt(andarValue.getText()),
        Integer.parseInt(numeroValue.getText()),
        moradorList,
        carroList
    );
    
    this.service.save(apt);
    this.finish();
  }
  
  private void finish() {
    ApartamentoController controller = new ApartamentoController();
    
    Stage stage = (Stage) submitButton.getScene().getWindow();
    stage.close();
  }
}
