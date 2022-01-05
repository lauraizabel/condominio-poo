package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ApartamentoTable {
  private final SimpleIntegerProperty id;
  private final SimpleStringProperty  bloco;
  private final SimpleIntegerProperty andar;
  private final SimpleIntegerProperty numero;
  private final SimpleStringProperty moradores;
  private final SimpleStringProperty carros;
  
  public ApartamentoTable(Integer id, String bloco, int andar, int numero, String moradores,
                          String carros) {
    this.id     = new SimpleIntegerProperty(id);
    this.bloco  = new SimpleStringProperty(bloco);
    this.andar  = new SimpleIntegerProperty(andar);
    this.numero = new SimpleIntegerProperty(numero);
    this.moradores = new SimpleStringProperty(moradores);
    this.carros = new SimpleStringProperty(carros);
  }
  
  public int getId() {
    return id.get();
  }
  
  public SimpleIntegerProperty idProperty() {
    return id;
  }
  
  public void setId(int id) {
    this.id.set(id);
  }
  
  public String getBloco() {
    return bloco.get();
  }
  
  public SimpleStringProperty blocoProperty() {
    return bloco;
  }
  
  public void setBloco(String bloco) {
    this.bloco.set(bloco);
  }
  
  public int getAndar() {
    return andar.get();
  }
  
  public SimpleIntegerProperty andarProperty() {
    return andar;
  }
  
  public void setAndar(int andar) {
    this.andar.set(andar);
  }
  
  public int getNumero() {
    return numero.get();
  }
  
  public SimpleIntegerProperty numeroProperty() {
    return numero;
  }
  
  public void setNumero(int numero) {
    this.numero.set(numero);
  }
  
  public String getMoradores() {
    return moradores.get();
  }
  
  public SimpleStringProperty moradoresProperty() {
    return moradores;
  }
  
  public void setMoradores(String moradores) {
    this.moradores.set(moradores);
  }
  
  public String getCarros() {
    return carros.get();
  }
  
  public SimpleStringProperty carrosProperty() {
    return carros;
  }
  
  public void setCarros(String carros) {
    this.carros.set(carros);
  }
  
}
