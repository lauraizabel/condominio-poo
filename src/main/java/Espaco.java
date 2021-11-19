public class Espaco {
    private  String id;
    private  String nome;
    private  int capacidade;
    private  boolean ocupado;

    public Espaco(String id, String nome, int capacidade, boolean ocupado) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.ocupado = ocupado;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}
