package dados;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Audited
public class Reserva extends CustomAuditory<Reserva> {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer idEspaco;
    private Integer idApartamento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataReserva;

    public Reserva() {
    }

    public Reserva(Integer idEspaco, Integer idApartamento, Date dataReserva) {
        this.idEspaco = idEspaco;
        this.idApartamento = idApartamento;
        this.dataReserva = dataReserva;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getIdEspaco() {
        return this.idEspaco;
    }

    public void setIdEspaco(Integer idEspaco) {
        this.idEspaco = idEspaco;
    }

    public Integer getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(Integer idApartamento) {
        this.idApartamento = idApartamento;
    }

    public Date getDataReserva() {
        return this.dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }
}
