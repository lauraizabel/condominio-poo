package dados;

public class Espaco {
    private String id;
    private String nome;
    private int capacidade;
    private boolean ocupado;
    private Double custoReserva;

    public Espaco(String id, String nome, int capacidade, boolean ocupado, Double custoReserva) {
        this.id = id;
        this.nome = nome;
        this.capacidade = capacidade;
        this.ocupado = ocupado;
        this.custoReserva = custoReserva;
    }

    public String getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isOcupado() {
        return this.ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Double getCustoReserva() {
        return this.custoReserva;
    }

    public void setOcupado(Double custoReserva) {
        this.custoReserva = custoReserva;
    }
}