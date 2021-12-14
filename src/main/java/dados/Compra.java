package dados;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Compra {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne()
    private Produto produto;
    private Integer quantidade;
    @ManyToOne(targetEntity = Funcionario.class, fetch = FetchType.LAZY)
    private Funcionario funcionario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    public Compra() {
    }

    public Compra(Produto produto, Integer quantidade, Date dataAlteracao, Funcionario funcionario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataAlteracao = dataAlteracao;
        this.funcionario = funcionario;
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

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }
}
