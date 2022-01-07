package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReservaTable {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty idEspaco;
    private final SimpleIntegerProperty idApartamento;
    private final SimpleStringProperty dataReserva;

    public ReservaTable(
            Integer id,
            Integer idEspaco,
            Integer idApartamento,
            String dataReserva
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.idEspaco = new SimpleIntegerProperty(idEspaco);
        this.idApartamento = new SimpleIntegerProperty(idApartamento);
        this.dataReserva = new SimpleStringProperty(dataReserva);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public Integer getIdEspaco() { return idEspaco.get(); }

    public SimpleIntegerProperty idEspacoProperty() {
        return idEspaco;
    }

    public void setIdEspaco(Integer idEspaco) {
        this.idEspaco.set(idEspaco);
    }

    public Integer getIdApartamento() { return idApartamento.get(); }

    public SimpleIntegerProperty idApartamentoProperty() {
        return idApartamento;
    }

    public void setIdApartamento(Integer idApartamento) {
        this.idApartamento.set(idApartamento);
    }

    public String getDataReserva() { return dataReserva.get(); }

    public SimpleStringProperty dataReservaProperty() {
        return dataReserva;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva.set(dataReserva);
    }
}

