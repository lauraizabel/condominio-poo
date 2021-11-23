package dados;

import java.util.Date;

public class Funcionario {
    private String id;
    private String cargo;
    private Double salario;
    private String endereco;
    private Date dataAdmissao;
    private Date dataEmissao;

    public Funcionario(String id, String cargo, Double salario, String endereco, Date dataAdmissao, Date dataEmissao){
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
        this.endereco = endereco;
        this.dataAdmissao = dataAdmissao;
        this.dataEmissao = dataEmissao;
    }

    public String getId() {
        return this.id;
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

    public Date getDataEmissao() {
        return this.dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
