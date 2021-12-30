package dados;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
public class Produto extends CustomAuditory<Pessoa> {

    @Id @GeneratedValue
    private Integer id;
    private String nome;
    @ManyToOne(targetEntity= Fornecedor.class, fetch= FetchType.LAZY)
    private Fornecedor fornecedor;
    private String codigo;
    private Integer pontoDePedido;
    private Integer quantidade;

    public Produto() {}

    public Produto(String nome, Fornecedor fornecedor, String codigo, Integer pontoDePedido, Integer quantidade) {
        this.nome = nome;
        this.fornecedor = fornecedor;
        this.codigo = codigo;
        this.pontoDePedido = pontoDePedido;
        this.quantidade = quantidade;
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

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getPontoDePedido() {
        return pontoDePedido;
    }

    public void setPontoDePedido(Integer pontoDePedido) {
        this.pontoDePedido = pontoDePedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
