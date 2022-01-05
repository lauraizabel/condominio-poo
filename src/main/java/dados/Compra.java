package dados;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Compra extends CustomAuditory<Compra> {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = Produto.class, fetch = FetchType.EAGER)
    private Produto produto;
    private Integer quantidade;
    @ManyToOne(targetEntity = Funcionario.class, fetch = FetchType.EAGER)
    private Funcionario funcionario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    private Double valorUnitario;

    public Compra() {
    }

    public Compra(Produto produto, Integer quantidade, Date dataAlteracao, Funcionario funcionario, Double valorUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataAlteracao = dataAlteracao;
        this.funcionario = funcionario;
        this.valorUnitario = valorUnitario;
    }

    public Integer getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
