package dados;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Servico {
    
    @Id @GeneratedValue
    private String id;
    private String descricao;
    private Float valor;
    private String codigo;
    private Funcionario requerente;
    private Fornecedor fornecedor;

    public Servico(String id, String descricao, Float valor, String codigo, Funcionario requerente, Fornecedor fornecedor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.codigo = codigo;
        this.requerente = requerente;
        this.fornecedor = fornecedor;
    }

    public String getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return this.valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Funcionario getRequerente() {
        return this.requerente;
    }

    public void setRequerente(Funcionario requerente) {
        this.requerente = requerente;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void SetFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


}