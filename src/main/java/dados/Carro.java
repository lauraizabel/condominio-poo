package dados;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Carro implements Serializable {
    @Id @GeneratedValue
    private Integer id;
    private String modelo;
    private String placa;

    public Carro() {}

    public Carro(String modelo, String placa){
        this.modelo = modelo;
        this.placa = placa;
    }

    public Integer getId() {
        return this.id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
