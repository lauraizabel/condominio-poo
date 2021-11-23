package dados;

public class Carro {
    private String id;
    private String modelo;
    private String placa;

    public Carro(String id, String modelo, String placa){
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getId() {
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