package tables;

import javafx.beans.property.SimpleStringProperty;

public class FornecedorTable {
    private final SimpleStringProperty nome;
    private final SimpleStringProperty cnpj;
    private final SimpleStringProperty endereco;
    private final SimpleStringProperty telefone;
    private final SimpleStringProperty email;

    public FornecedorTable(
            String nome,
            String cnpj,
            String endereco,
            String telefone,
            String email
    ) {
        this.nome = new SimpleStringProperty(nome);
        this.cnpj = new SimpleStringProperty(cnpj);
        this.endereco = new SimpleStringProperty(endereco);
        this.telefone = new SimpleStringProperty(telefone);
        this.email = new SimpleStringProperty(email);
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

    public String getCnpj() {
        return cnpj.get();
    }

    public SimpleStringProperty cnpjProperty() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj.set(cnpj);
    }

    public String getEndereco() {
        return endereco.get();
    }

    public SimpleStringProperty enderecoProperty() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
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

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
