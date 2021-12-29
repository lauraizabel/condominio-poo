package dados;

import org.hibernate.envers.Audited;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Audited
public class Apartamento {
    
    @Id @GeneratedValue
    private Integer id;
    private String bloco;
    private int andar;
    private int numero;
    private ArrayList<Morador> moradores;
    private ArrayList<Carro> carros;

    public Apartamento() {}

    public Apartamento(String bloco, int andar, int numero, ArrayList<Morador> moradores, ArrayList<Carro> carros) {
        this.bloco = bloco;
        this.andar = andar;
        this.numero = numero;
        this.moradores = moradores;
        this.carros = carros;
    }

    public Integer getId() {
        return this.id;
    }

    public String getBloco() {
        return this.bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public int getAndar() {
        return this.andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getNumApartamento() {
        return this.numero;
    }

    public void setNumApartamento(int apartamento) {
        this.numero = apartamento;
    }

    public ArrayList<Morador> getMoradores() {
        return this.moradores;
    }

    public void setMoradores(ArrayList<Morador> moradores) {
        this.moradores = moradores;
    }

    public ArrayList<Carro> getCarros() {
        return this.carros;
    }

    public void setCarros(ArrayList<Carro> carros) {
        this.carros = carros;
    }
}
