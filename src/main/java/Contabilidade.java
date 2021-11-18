import java.util.ArrayList;
import java.util.Date;

public class Contabilidade {
    private ArrayList<Double> entradas;
    private ArrayList<Double> saidas;
    private Double saldoInicial;
    private Double saldoAtual;
    private Date data;

    public Contabilidade(ArrayList<Double> entradas, ArrayList<Double> saidas, Double saldoInicial, Double saldoAtual, Date data) {
        this.entradas = entradas;
        this.saidas = saidas;
        this.saldoAtual = saldoAtual;
        this.saldoInicial = saldoInicial;
        this.data = data;
    }

    public ArrayList<Double> getEntradas(){
        return this.entradas;
    }

    public void setEntradas(ArrayList<Double> entradas) {
        this.entradas = entradas;
    }

    public ArrayList<Double> getSaidas(){
        return this.saidas;
    }

    public void setSaidas(ArrayList<Double> saidas) {
        this.entradas = saidas;
    }

    public Double getSaldoInicial(){
        return this.saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoAtual(){
        return this.saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data){
        this.data = data;
    }

}
