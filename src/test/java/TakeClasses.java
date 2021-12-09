import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.github.javafaker.Faker;

import dados.AcessoPermitido;
import dados.Almoxarifado;
import dados.Apartamento;
import dados.Auditoria;
import dados.Carro;
import dados.Espaco;
import dados.Fornecedor;
import dados.Funcionario;
import dados.Morador;
import dados.Pedido;
import dados.Produto;
import dados.Reserva;
import dados.Servico;

public class TakeClasses {
  static Faker faker = new Faker();
  private Integer id;
  private Integer idAcesso;
  private String descricao;
  private String name;
  private String phone;
  private String email;
  private String cpf;
  private String cnpj;
  private String cargo;
  private double salario;
  private String endereco;
  private Date data;
  private Double valor;
  private String modelo;
  private String placa;
  private int andar;
  private int numApartamento;
  private boolean ocupado;
  private boolean permitido;
  private int capacidade;
  private Double custoReserva;
  private String nomeEspaco;
  private String tabelaAlterada;
  private String bloco;
  private String codigo;
  private Integer idEspaco;
  private Integer idApartamento;

  private Funcionario funcionario;
  private Morador morador;
  private Fornecedor fornecedor;
  private Produto produto;
  private Pedido pedido;
  private AcessoPermitido acessoPermitido;
  private Apartamento apartamento;
  private Almoxarifado almoxarifado;
  private Carro carro;
  private Espaco espaco;
  private Auditoria auditoria;
  private Servico servico;
  private Reserva reserva;
  
  

  public TakeClasses() {
    this.id = faker.random().nextInt(1, 100);
    this.idAcesso = faker.random().nextInt(1, 100);
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
    this.modelo = faker.random().toString();
    this.placa = faker.random().toString();
    this.andar = faker.random().nextInt(1,20);
    this.ocupado = faker.bool().bool();
    this.permitido = faker.bool().bool();
    this.capacidade = faker.random().nextInt(20, 1000);
    this.custoReserva = faker.random().nextDouble();
    this.nomeEspaco = faker.lorem().word();
    this.tabelaAlterada = faker.lorem().word();
    this.bloco = faker.lorem().characters();
    this.numApartamento = faker.random().nextInt(1, 500);
    this.descricao = faker.random().toString();
    this.codigo = faker.random().toString();
    this.idEspaco = faker.random().nextInt(1, 100);
    this.idApartamento = faker.random().nextInt(1, 100);
    }

  public Funcionario getFuncionario() {
    return this.funcionario;
  }

  public Espaco getEspaco() {
    return this.espaco;
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

  public AcessoPermitido getAcessoPermitido() {
    return this.acessoPermitido;
  }

  public Apartamento getApartamento() {
    return this.apartamento;
  }

  public Carro getCarro() {
    return this.carro;
  }

  public Servico getsServico() {
    return this.servico;
  }

  public Reserva getReserva() {
    return this.reserva;
  }

  public Almoxarifado getAlmoxarifado(){
    return this.almoxarifado;
  }

  public Integer getId() {
    return id;
  }

  public Integer getIdAcesso() {
    return this.idAcesso;
  }

  public Integer getIdApApartamento() {
    return this.idApartamento;
  }

  public Integer getIdEspaco() {
    return this.idEspaco;
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

  public String getCnpj() {
    return cnpj;
  }

  public String getModelo() {
    return modelo;
  }

  public String getPlaca() {
    return placa;
  }

  public String getNomeEspaco() {
    return nomeEspaco;
  }

  public int getCapacidade() {
    return capacidade;
  }

  public boolean isOcupado() {
    return ocupado;
  }

  public boolean isPermitido() {
    return this.permitido;
  }

  public Double getCustoReserva() {
    return custoReserva;
  }

  public String getTabelaAlterada() {
    return tabelaAlterada;
  }

  public String getBloco() {
     return bloco;
  }

  public int getNumApartamento() {
    return numApartamento;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public Double getValor() {
    return this.valor;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public Funcionario funcionario() {
    Funcionario funcionario = new Funcionario(this.name, this.phone, this.email, this.cpf, this.cargo,
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
    Produto produto = new Produto(this.name, v, this.fornecedor(), cod);
    this.produto = produto;
    return produto;
  }

  public Fornecedor fornecedor() {
    Fornecedor fornecedor = new Fornecedor(this.name, this.cnpj, this.endereco, this.phone, this.email);
    this.fornecedor = fornecedor;
    return fornecedor;
  }

  public Pedido pedido() {
    Funcionario f = this.funcionario();
    Produto p1 = this.produto();
    Produto p2 = this.produto();
    ArrayList<Produto> produtos = new ArrayList<Produto>();
    produtos.add(p1);
    produtos.add(p2);
    Pedido pedido = new Pedido(f, produtos);
    this.pedido = pedido;
    return pedido;
  }

  public Carro carro() { 
    Carro carro = new Carro(this.modelo, this.placa);
    this.carro = carro;
    return carro;
  }

  public Espaco espaco() {
    Espaco espaco = new Espaco(this.nomeEspaco, this.capacidade, this.ocupado, this.custoReserva);
    this.espaco = espaco;
    return espaco;
  }

  public Auditoria auditoria() {
    Auditoria auditoria = new Auditoria(this.tabelaAlterada, this.id, this.data);
    this.auditoria = auditoria;
    return auditoria;
  }

  public Apartamento apartamento() { 
    ArrayList<Morador> moradores = new ArrayList<Morador>();
    moradores.add(morador());
    ArrayList<Carro> carros = new ArrayList<Carro>();
    carros.add(carro());

    Apartamento apartamento = new Apartamento(this.bloco, this.andar, this.numApartamento, moradores, carros);
    this.apartamento = apartamento;
    return apartamento;

  }

  public Servico servico() {
    Servico servico = new Servico(this.descricao, this.valor, this.codigo, this.funcionario, this.fornecedor);
    this.servico = servico;
    return servico;
  }

  public Reserva reserva(){
    Reserva reserva = new Reserva(this.idEspaco, this.idApartamento, this.data);
    this.reserva = reserva;
    return reserva;
  }

  public AcessoPermitido acessoPermitido() {
    AcessoPermitido acessoPermitido = new AcessoPermitido(this.name, this.cpf, this.apartamento, this.phone,
      this.email, this.morador, this.permitido, this.idAcesso);
    
      this.acessoPermitido = acessoPermitido;
      return acessoPermitido;
  }

  public Almoxarifado almoxarifado() {
    ArrayList<Produto> produtos = new ArrayList<>();
    produtos.add(produto());
    Almoxarifado almoxarifado = new Almoxarifado(produtos);
    this.almoxarifado = almoxarifado;
    return almoxarifado;
  }

}
