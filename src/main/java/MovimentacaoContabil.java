import java.util.Date;

public class MovimentacaoContabil {
    private String id; 
    private Date data;
    private Double valor;
    private Conta conta;

    public MovimentacaoContabil(String id, Date data, Double valor, Conta conta) {
        this.id = id;
        this.data = data;
        this.valor = valor;
        this.conta = conta;
    }

    public String getId() {
        return this.id;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data){
        this.data = data;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta){
        this.conta = conta;
    }
}
