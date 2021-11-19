public class Carro {
    private String id;
    private String modelo;
    private String placa;
    private Morador dono;

    public Carro(String id, String modelo, String placa, Morador dono){
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.dono = dono;
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

    public Morador getDono() {
        return this.dono;
    }

    public void setDono(Morador dono) {
        this.dono = dono;
    }    
}
