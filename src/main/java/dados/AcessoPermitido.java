package dados;

public class AcessoPermitido extends Pessoa {

    private String idAcesso;
    private Apartamento apartamento;
    private Boolean permitido;

    public AcessoPermitido(String id, String nome, String cpf, Apartamento apartamento, String telefone, String email, Morador permissor, Boolean permitido, String idAcesso){
        super(id, nome, telefone, email, cpf);
        this.apartamento = apartamento;
        this.permitido = permitido;
        this.idAcesso = idAcesso;
    }

    public String getIdAcesso() {
        return this.idAcesso;
    }

    public Apartamento getApartamento(){
        return this.apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }

    public Boolean isPermitido() {
        return this.permitido;
    }

    public void setPermitido(Boolean permitido) {
        this.permitido = permitido;
    }

}
