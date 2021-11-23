package dados;

public class Morador extends Pessoa {

    private Apartamento apartamento;

    public Morador(Apartamento apartamento, String id, String nome, String telefone, String email, String cpf) {
        super(id, nome, telefone, email, cpf);
        this.apartamento = apartamento;
    }

    public Apartamento getApartamento() {
        return this.apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento = apartamento;
    }
}
