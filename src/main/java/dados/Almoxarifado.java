package dados;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Audited
public class Almoxarifado extends CustomAuditory<Almoxarifado> {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(targetEntity = Produto.class, fetch = FetchType.EAGER)
    private Produto produto;
    @ManyToOne(targetEntity = Funcionario.class, fetch = FetchType.EAGER)
    private Funcionario funcionario;
    private Integer quantidadeAdicionada;
    private Integer quantidadeRemovida;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    public Almoxarifado() {
    }

    public Almoxarifado(Produto produto, Funcionario funcionario, Integer quantidadeAdicionada,
            Integer quantidadeRemovida, Date dataAlteracao) {
        this.produto = produto;
        this.funcionario = funcionario;
        this.quantidadeAdicionada = quantidadeAdicionada;
        this.quantidadeRemovida = quantidadeRemovida;
        this.dataAlteracao = dataAlteracao;
    }

    public Integer getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getQuantidadeAdicionada() {
        return quantidadeAdicionada;
    }

    public void setQuantidadeAdicionada(Integer quantidadeAdicionada) {
        this.quantidadeAdicionada = quantidadeAdicionada;
    }

    public Integer getQuantidadeRemovida() {
        return quantidadeRemovida;
    }

    public void setQuantidadeRemovida(Integer quantidadeRemovida) {
        this.quantidadeRemovida = quantidadeRemovida;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
