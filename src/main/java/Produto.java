public class Produto {
    private Number id;
    private String nome;
    private Float valor;
    private Fornecedor fornecedor;
    private String codigo;

    public Produto(Number id, String nome, Float valor, Fornecedor fornecedor, String codigo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.fornecedor = fornecedor;
        this.codigo = codigo;
    }

    public Number getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
