package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MoradorTable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty telefone;
    private final SimpleStringProperty email;
    private final SimpleStringProperty cpf;

    public MoradorTable(
            Integer id,
            String nome,
            String telefone,
            String email,
            String cpf
    ) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.telefone = new SimpleStringProperty(telefone);
        this.email = new SimpleStringProperty(email);
        this.cpf = new SimpleStringProperty(cpf);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNome() { return nome.get(); }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getEmail() { return email.get(); }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelefone() { return telefone.get(); }

    public SimpleStringProperty telefoneProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
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
}
