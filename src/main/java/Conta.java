public class Conta {
    private String id;
    private String banco;
    private String agencia;
    private Double saldo;
    private String conta;
    private String tipo;

    public Conta(String id, String banco, String agencia, Double saldo, String conta, String tipo) {
        this.id = id;
        this.banco = banco;
        this.agencia = agencia;
        this.saldo = saldo;
        this.conta = conta;
        this.tipo = tipo;
    }

    public String getId() {
        return this.id;
    }

    public String getBanco() {
        return this.banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getConta() {
        return this.conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
