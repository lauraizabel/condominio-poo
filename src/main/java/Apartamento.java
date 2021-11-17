public class Apartamento {
    String id;
    String bloco;
    int andar;
    int apartamento;

    public Apartamento(String id, String bloco, int andar, int apartamento) {
        this.id = id;
        this.bloco = bloco;
        this.andar = andar;
        this.apartamento = apartamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public int getAndar() {
        return andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }

    public int getApartamento() {
        return apartamento;
    }

    public void setApartamento(int apartamento) {
        this.apartamento = apartamento;
    }
}
