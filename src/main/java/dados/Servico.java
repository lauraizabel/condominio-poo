package dados;

import org.hibernate.envers.Audited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited(targetAuditMode = NOT_AUDITED)
public class Servico extends CustomAuditory<Servico> {

    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;
    private Double valor;
    private String codigo;
    @ManyToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY)
    private Funcionario requerente;
    @ManyToOne(targetEntity = Fornecedor.class, fetch = FetchType.LAZY)
    private Fornecedor fornecedor;

    public Servico() {
    }

    public Servico(String descricao, Double valor, String codigo, Funcionario requerente, Fornecedor fornecedor) {
        this.descricao = descricao;
        this.valor = valor;
        this.codigo = codigo;
        this.requerente = requerente;
        this.fornecedor = fornecedor;
    }

    public Integer getId() {
        return this.id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
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