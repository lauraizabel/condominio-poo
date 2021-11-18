public class Carro {
    String id;
    String modelo;
    String placa;
    Morador dono;

    public Carro(String id, String modelo, String placa, Morador dono){
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.dono = dono;
    }

    public String getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Morador getDono() {
        return dono;
    }

    public void setDono(Morador dono) {
        this.dono = dono;
    }    
}
