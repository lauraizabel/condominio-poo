package controllers.modals;

import business.ApartamentoService;
import business.CarroServices;
import business.MoradorService;
import controllers.views.ApartamentoController;
import dados.Apartamento;
import dados.Carro;
import dados.Morador;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

public class EditApartamentoController implements Initializable {
  ApartamentoService service        = new ApartamentoService();
  CarroServices      carroService   = new CarroServices();
  MoradorService     moradorService = new MoradorService();
  
  private static ArrayList<Morador> moradores;
  private static ArrayList<Carro>   carros;
  private static Apartamento        apartamento;
  private static ObservableList<String> selected;
  private static ObservableList<String> carsSelected;
  
  @FXML
  TextField blocoValue;
  
  @FXML
  TextField andarValue;
  
  @FXML
  TextField numeroValue;
  
  @FXML
  CheckComboBox<String> moradoresValue;
  
  @FXML
  CheckComboBox<String> carrosValue;
  
  @FXML
  private Button submitButton;
  
  public EditApartamentoController(Apartamento apartamento) {
    this.apartamento = apartamento;
  }
  
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    this.carrosValue.getItems().addAll(FXCollections.observableList(getCarros()));
    carrosValue.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c ->
        carsSelected = carrosValue.getCheckModel().getCheckedItems()
    );
    
    ArrayList<Integer> moradorIndexes = getMoradoresIndex(apartamento.getMoradores());
    for (Integer i: moradorIndexes) {
      this.moradoresValue.getCheckModel().check(i);
    }
    
    ArrayList<Integer> carroIndexes = getCarrosIndex(apartamento.getCarros());
    for (Integer i: carroIndexes) {
      this.carrosValue.getCheckModel().check(i);
    }
    
    this.moradoresValue.getItems().addAll(getMoradores());
    moradoresValue.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) c ->
        selected = moradoresValue.getCheckModel().getCheckedItems()
    );
  }
  
  private ArrayList<Integer> getMoradoresIndex(List<Morador> moradores) {
    ArrayList<Integer> indexes = new ArrayList<>();
    
    for(int i = 0; i < this.moradoresValue.getItems().size(); i++) {
      for (Morador morador: moradores) {
        if (this.moradoresValue.getItems().get(i).equals(morador.getNome())) {
          indexes.add(i);
        }
      }
    }
    return indexes;
  }
  
  private ArrayList<Integer> getCarrosIndex(List<Carro> carros) {
    ArrayList<Integer> indexes = new ArrayList<>();
    
    for (int i = 0; i < this.carrosValue.getItems().size(); i++) {
      for (Carro carro: carros) {
        if (this.carrosValue.getItems().get(i).equals(carro.getModelo())) {
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
  public void handleSubmit(ActiveEvent e) {
    ObservableList<Integer> carrosIdx = carrosValue.getCheckModel().getCheckedIndices();
    ObservableList<Integer> moradoresIdx = moradoresValue.getCheckModel().getCheckedIndices();
    ArrayList<Carro> carroList = new ArrayList<>();
    ArrayList<Morador> moradorList = new ArrayList<>();
    
    for (Integer i: carrosIdx) {
      carroList.add(carros.get(i));
    }
    
    for (Integer i: moradoresIdx) {
      moradorList.add(moradores.get(i));
    }
    
    apartamento.setBloco(andarValue.getText());
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
