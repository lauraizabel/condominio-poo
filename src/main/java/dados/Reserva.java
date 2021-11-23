package dados;

import java.util.Date;

public class Reserva {
    private String id;
    private String idEspaco;
    private String idApartamento;
    private Date dataReserva;

    public Reserva(String id, String idEspaco, String idApartamento, Date dataReserva) {
        this.id = id;
        this.idEspaco = idEspaco;
        this.idApartamento = idApartamento;
        this.dataReserva = dataReserva;
    }

    public String getId() {
        return this.id;
    }

    public String getIdEspaco() {
        return this.idEspaco;
    }

    public void setIdEspaco(String idEspaco) {
        this.idEspaco = idEspaco;
    }

    public String getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(String idApartamento) {
        this.idApartamento = idApartamento;
    }

    public Date getDataReserva() {
        return this.dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }
}