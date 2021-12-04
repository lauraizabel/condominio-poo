
import java.util.ArrayList;
import java.util.Date;

import com.github.javafaker.Faker;

import dados.Fornecedor;
import dados.Funcionario;
import dados.Morador;
import dados.Pedido;
import dados.Produto;

public class TakeClasses {
  static Faker faker = new Faker();
  private Integer id;
  private String name;
  private String phone;
  private String email;
  private String cpf;
  private String cnpj;
  private String cargo;
  private double salario;
  private String endereco;
  private Date data;
  private double valor;

  private Funcionario funcionario;
  private Morador morador;
  private Fornecedor fornecedor;
  private Produto produto;
  private Pedido pedido;

  public TakeClasses() {
    this.id = faker.random().nextInt(1, 100);
    this.name = faker.name().firstName();
    this.phone = faker.phoneNumber().cellPhone();
    this.email = faker.internet().emailAddress();
    this.cpf = faker.random().toString();
    this.cnpj = faker.random().toString();
    this.cargo = faker.commerce().department();
    this.salario = faker.random().nextDouble();
    this.endereco = faker.address().fullAddress();
    this.data = faker.date().birthday();
    this.valor = faker.random().nextDouble();
  }

  public Funcionario getFuncionario() {
    return this.funcionario;
  }

  public Morador getMorador() {
    return this.morador;
  }

  public Fornecedor getFornecedor() {
    return this.fornecedor;
  }

  public Produto getProduto() {
    return this.produto;
  }

  public Pedido getPedido() {
    return this.pedido;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getCpf() {
    return cpf;
  }

  public String getCargo() {
    return cargo;
  }

  public double getSalario() {
    return salario;
  }

  public String getEndereco() {
    return endereco;
  }

  public Date getData() {
    return data;
  }

  public Funcionario funcionario() {
    Funcionario funcionario = new Funcionario(this.id, this.name, this.phone, this.email, this.cpf, this.cargo,
        this.salario, this.endereco, this.data, this.data);
    this.funcionario = funcionario;
    return funcionario;
  }

  public Morador morador() {
    Morador morador = new Morador(this.id, this.name, this.phone, this.email, this.cpf);
    this.morador = morador;
    return morador;
  }

  public Produto produto() {
    String cod = this.id.toString();
    Float v = 10.0f;
    Produto produto = new Produto(this.id, this.name, v, this.fornecedor(), cod);
    this.produto = produto;
    return produto;
  }

  public Fornecedor fornecedor() {
    Fornecedor fornecedor = new Fornecedor(this.id, this.name, this.cnpj, this.endereco, this.phone, this.email);
    return fornecedor;
  }

  public Pedido pedido() {
    Funcionario f = this.funcionario();
    Produto p1 = this.produto();
    Produto p2 = this.produto();
    ArrayList<Produto> produtos = new ArrayList<Produto>();
    produtos.add(p1);
    produtos.add(p2);
    Pedido pedido = new Pedido(this.id, f, produtos);
    this.pedido = pedido;
    return pedido;
  }
}
