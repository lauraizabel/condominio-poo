package tables;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FuncionarioTable {
  private final SimpleIntegerProperty id;
  private final   SimpleStringProperty  nome;
  private final SimpleStringProperty cpf;
  private final SimpleStringProperty email;
  private final SimpleStringProperty telefone;
  private final SimpleStringProperty cargo;
  private final SimpleDoubleProperty salario;
  private final SimpleStringProperty endereco;
  private final SimpleStringProperty dataAdmissao;
  private final SimpleStringProperty dataDemissao;
  
  public FuncionarioTable(Integer id, String nome, String cpf, String email, String telefone,
                          String cargo, Double salario, String endereco, String dataAdmissao,
                          String dataDemissao) {
    this.id           = new SimpleIntegerProperty(id);
    this.nome         = new SimpleStringProperty(nome);
    this.cpf          = new SimpleStringProperty(cpf);
    this.email        = new SimpleStringProperty(email);
    this.telefone     = new SimpleStringProperty(telefone);
    this.cargo        = new SimpleStringProperty(cargo);
    this.salario      = new SimpleDoubleProperty(salario);
    this.endereco     = new SimpleStringProperty(endereco);
    this.dataAdmissao = new SimpleStringProperty(dataAdmissao);
    this.dataDemissao = new SimpleStringProperty(dataDemissao);
  }
  
  public int getId() {
    return id.get();
  }
  
  public SimpleIntegerProperty idProperty() {
    return id;
  }
  
  public void setId(int id) {
    this.id.set(id);
  }
  
  public String getNome() {
    return nome.get();
  }
  
  public SimpleStringProperty nomeProperty() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome.set(nome);
  }
  
  public String getCpf() {
    return cpf.get();
  }
  
  public SimpleStringProperty cpfProperty() {
    return cpf;
  }
  
  public void setCpf(String cpf) {
    this.cpf.set(cpf);
  }
  
  public String getEmail() {
    return email.get();
  }
  
  public SimpleStringProperty emailProperty() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email.set(email);
  }
  
  public String getTelefone() {
    return telefone.get();
  }
  
  public SimpleStringProperty telefoneProperty() {
    return telefone;
  }
  
  public void setTelefone(String telefone) {
    this.telefone.set(telefone);
  }
  
  public String getCargo() {
    return cargo.get();
  }
  
  public SimpleStringProperty cargoProperty() {
    return cargo;
  }
  
  public void setCargo(String cargo) {
    this.cargo.set(cargo);
  }
  
  public double getSalario() {
    return salario.get();
  }
  
  public SimpleDoubleProperty salarioProperty() {
    return salario;
  }
  
  public void setSalario(double salario) {
    this.salario.set(salario);
  }
  
  public String getEndereco() {
    return endereco.get();
  }
  
  public SimpleStringProperty enderecoProperty() {
    return endereco;
  }
  
  public void setEndereco(String endereco) {
    this.endereco.set(endereco);
  }
  
  public String getDataAdmissao() {
    return dataAdmissao.get();
  }
  
  public SimpleStringProperty dataAdmissaoProperty() {
    return dataAdmissao;
  }
  
  public void setDataAdmissao(String dataAdmissao) {
    this.dataAdmissao.set(dataAdmissao);
  }
  
  public String getDataDemissao() {
    return dataDemissao.get();
  }
  
  public SimpleStringProperty dataDemissaoProperty() {
    return dataDemissao;
  }
  
  public void setDataDemissao(String dataDemissao) {
    this.dataDemissao.set(dataDemissao);
  }
  
}
