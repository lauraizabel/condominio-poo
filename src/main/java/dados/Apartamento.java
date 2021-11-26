package dados;

import java.util.ArrayList;

public class Apartamento {
    private String id;
    private String bloco;
    private int andar;
    private int apartamento;
    private ArrayList<Morador> moradores;
    private ArrayList<Carro> carros;

    public Apartamento(String id, String bloco, int andar, int apartamento, ArrayList<Morador> moradores, ArrayList<Carro> carros) {
        this.id = id;
        this.bloco = bloco;
        this.andar = andar;
        this.apartamento = apartamento;
        this.moradores = moradores;
        this.carros = carros;
    }

    public String getId() {
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

    public int getApartamento() {
        return this.apartamento;
    }

    public void setApartamento(int apartamento) {
        this.apartamento = apartamento;
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
