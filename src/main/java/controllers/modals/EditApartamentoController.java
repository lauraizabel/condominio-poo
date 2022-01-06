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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

public class EditApartamentoController implements Initializable {
  ApartamentoService service        = new ApartamentoService();
  CarroServices      carroService   = new CarroServices();
  MoradorService     moradorService = new MoradorService();
  
  private static ArrayList<Morador> moradores;
  private static ArrayList<Carro>   carros;
  private static Apartamento        apartamento;
  private static ObservableList<String> moradorSelected;
  private static ObservableList<String> carroSelected;
  
  @FXML
  TextField blocoValue;
  
  @FXML
  TextField andarValue;
  
  @FXML
  TextField numeroValue;
  
  @FXML
  CheckComboBox<String> moradorValues
          ;
  
  @FXML
  CheckComboBox<String> carroValues;
  
  @FXML
  private Button submitButton;
  
  public EditApartamentoController(Apartamento apartamento) {
    this.apartamento = apartamento;
  }
  
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
    
    ArrayList<Integer> moradorIndexes = getMoradoresIndex(apartamento.getMoradores());
    for (Integer i: moradorIndexes) {
      this.moradorValues.getCheckModel().check(i);
    }
    
    ArrayList<Integer> carroIndexes = getCarrosIndex(apartamento.getCarros());
    for (Integer i: carroIndexes) {
      this.carroValues.getCheckModel().check(i);
    }
  }
  
  private ArrayList<Integer> getMoradoresIndex(List<Morador> moradores) {
    ArrayList<Integer> indexes = new ArrayList<>();
    for(int i = 0; i < this.moradorValues
            .getItems().size(); i++) {
      for (Morador morador: moradores) {
        if (this.moradorValues
                .getItems().get(i).equals(morador.getNome())) {
          indexes.add(i);
        }
      }
    }
    return indexes;
  }
  
  private ArrayList<Integer> getCarrosIndex(List<Carro> carros) {
    ArrayList<Integer> indexes = new ArrayList<>();
    
    for (int i = 0; i < this.carroValues.getItems().size(); i++) {
      for (Carro carro: carros) {
        if (this.carroValues.getItems().get(i).equals(carro.getModelo())) {
          indexes.add(i);
        }
      }
    }
    return indexes;
  }
  
  private ObservableList<String> getCarros() {
    carros = this.carroService.getAll();
  
    ArrayList<String> carrosList = new ArrayList<>();
    carros.forEach(carro -> carrosList.add(carro.getModelo()));
  
    return FXCollections.observableArrayList(carrosList);
  }
  
  private ObservableList<String> getMoradores() {
    moradores = this.moradorService.getAll();
    ArrayList<String> moradoresList = new ArrayList<>();
    
    moradores.forEach(morador -> moradoresList.add(morador.getNome()));
    
    return FXCollections.observableArrayList(moradoresList);
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
    
    apartamento.setBloco(blocoValue.getText());
    apartamento.setAndar(Integer.parseInt(andarValue.getText()));
    apartamento.setNumApartamento(Integer.parseInt(numeroValue.getText()));
    apartamento.setCarros(carroList);
    apartamento.setMoradores(moradorList);
    
    service.update(apartamento);
    
    this.finish();
  }
  
  private void finish() {
    ApartamentoController controller = new ApartamentoController();
    controller.reloadItems();
    
    Stage stage = (Stage) submitButton.getScene().getWindow();
    stage.close();
  }
}
