package tables;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AcessoTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty cpf;
    private final SimpleStringProperty email;
    private final SimpleStringProperty telefone;
    private final SimpleStringProperty permitido;
    private final SimpleIntegerProperty apartamento;

    public AcessoTable(
            Integer id,
            String nome,
            String cpf,
            String email,
            String telefone,
            Integer apartamento,
            String permitido
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.cpf = new SimpleStringProperty(cpf);
        this.email = new SimpleStringProperty(email);
        this.telefone = new SimpleStringProperty(telefone);
        this.apartamento = new SimpleIntegerProperty(apartamento);
        this.permitido = new SimpleStringProperty(permitido);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getCpf() {
        return cpf.get();
    }

    public SimpleStringProperty cpfProperty() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelefone() {
        return telefone.get();
    }

    public SimpleStringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public String isPermitido() {
        return permitido.get();
    }

    public SimpleStringProperty permitidoProperty() {
        return permitido;
    }

    public void setPermitido(String permitido) {
        this.permitido.set(permitido);
    }

    public int getApartamento() {
        return apartamento.get();
    }

    public SimpleIntegerProperty apartamentoProperty() {
        return apartamento;
    }

    public void setApartamento(int apartamento) {
        this.apartamento.set(apartamento);
    }
}
