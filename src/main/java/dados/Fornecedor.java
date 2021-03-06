package dados;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Audited
@Table
public class Fornecedor extends CustomAuditory<Fornecedor> {

    @Id @GeneratedValue
    private Integer id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private String email;

    public Fornecedor() {}

    public Fornecedor(String nome, String cnpj, String endereco, String telefone, String email) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {this.id = id;}
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
}
