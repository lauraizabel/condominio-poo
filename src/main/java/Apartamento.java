public class Apartamento {
    private String id;
    private String bloco;
    private int andar;
    private int apartamento;

    public Apartamento(String id, String bloco, int andar, int apartamento) {
        this.id = id;
        this.bloco = bloco;
        this.andar = andar;
        this.apartamento = apartamento;
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
}
