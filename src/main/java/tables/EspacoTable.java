package tables;

import javafx.beans.property.*;

public class EspacoTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;
    private final SimpleIntegerProperty capacidade;
    private final SimpleStringProperty ocupado;
    private final SimpleDoubleProperty custoReserva;

    public EspacoTable(
            Integer id,
            String nome,
            Integer capacidade,
            String ocupado,
            Double custoReserva
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.capacidade = new SimpleIntegerProperty(capacidade);
        this.ocupado = new SimpleStringProperty(ocupado);
        this.custoReserva = new SimpleDoubleProperty(custoReserva);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public int getCapacidade() {
        return capacidade.get();
    }

    public SimpleIntegerProperty capacidadeProperty() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade.set(capacidade);
    }

    public String isOcupado() {
        return ocupado.get();
    }

    public SimpleStringProperty ocupadoProperty() {
        return ocupado;
    }

    public void setOcupado(String ocupado) {
        this.ocupado.set(ocupado);
    }

    public double getCustoReserva() {
        return custoReserva.get();
    }

    public SimpleDoubleProperty custoReservaProperty() {
        return custoReserva;
    }

    public void setCustoReserva(double custoReserva) {
        this.custoReserva.set(custoReserva);
    }
}
