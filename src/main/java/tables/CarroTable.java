package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CarroTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty modelo;
    private final SimpleStringProperty placa;

    public CarroTable(
            Integer id,
            String modelo,
            String placa
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.modelo = new SimpleStringProperty(modelo);
        this.placa = new SimpleStringProperty(placa);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getModelo() {
        return modelo.get();
    }

    public SimpleStringProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getPlaca() {
        return placa.get();
    }

    public SimpleStringProperty placaProperty() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa.set(placa);
    }
}
