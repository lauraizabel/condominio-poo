package dados;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Espaco {
    @Id @GeneratedValue
    private Integer id;
    private String nome;
    private int capacidade;
    private boolean ocupado;
    private Double custoReserva;

    public Espaco() {}

    public Espaco(String nome, int capacidade, boolean ocupado, Double custoReserva) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.ocupado = ocupado;
        this.custoReserva = custoReserva;
    }

    public Integer getId() {
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

    public void setCustoReserva(Double custoReserva) {
        this.custoReserva = custoReserva;
    }
}
