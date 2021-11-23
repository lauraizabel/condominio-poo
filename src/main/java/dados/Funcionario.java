package dados;

import java.util.Date;

public class Funcionario extends Pessoa {
    private String cargo;
    private Double salario;
    private String endereco;
    private Date dataAdmissao;
    private Date dataDemissao;

    public Funcionario(
            String id,
            String nome,
            String telefone,
            String email,
            String cpf,
            String cargo,
            Double salario,
            String endereco,
            Date dataAdmissao,
            Date dataEmissao
    ){
        super(id, nome, telefone, email, cpf);
        this.cargo = cargo;
        this.salario = salario;
        this.endereco = endereco;
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataEmissao;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return this.salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataAdmissao() {
        return this.dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return this.dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }
}
