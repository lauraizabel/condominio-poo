package dados;

import java.util.Date;

public class Reserva {
    private Number id;
    private Number idEspaco;
    private Number idMorador;
    private Date dataReserva;

    public Reserva(Number id, Number idEspaco, Number idMorador, Date dataReserva) {
        this.id = id;
        this.idEspaco = idEspaco;
        this.idMorador = idMorador;
        this.dataReserva = dataReserva;
    }

    public Number getId() {
        return this.id;
    }

    public Number getIdEspaco() {
        return this.idEspaco;
    }

    public void setIdEspaco(Number idEspaco) {
        this.idEspaco = idEspaco;
    }

    public Number getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(Number idMorador) {
        this.idMorador = idMorador;
    }

    public Date getDataReserva() {
        return this.dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }
}
