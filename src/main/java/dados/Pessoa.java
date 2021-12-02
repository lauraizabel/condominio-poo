package dados;
import javax.persistence.*;

@MappedSuperclass
public abstract class Pessoa {
    @Id @GeneratedValue
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;

    public Pessoa() {}

    public Pessoa(Integer id, String nome, String telefone, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
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

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
