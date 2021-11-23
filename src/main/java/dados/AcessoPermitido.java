package dados;

public class AcessoPermitido extends Pessoa {

    private String id;
    private Apartamento apartamento;
    private Boolean permitido;

    public AcessoPermitido(String id, String nome, String cpf, Apartamento apartamento, String telefone, String email, Morador permissor, Boolean permitido){
        super(id, nome, telefone, email, cpf);
        this.apartamento = apartamento;
        this.permitido = permitido;
    }

    public String getId() {
        return id;
    }

    public Apartamento getApartamento(){
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento =apartamento;
    }

    public Boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(Boolean permitido) {
        this.permitido = permitido;
    }

}
