public class AcessoPermitido {
    private String id;
    private String nome;
    private String cpf;
    private Apartamento apartamento;
    private String telefone;
    private String email;
    private Morador permissor;
    private Boolean permitido;

    public AcessoPermitido(String id, String nome, String cpf, Apartamento apartamento, String telefone, String email, Morador permissor, Boolean permitido){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.apartamento = apartamento;
        this.telefone = telefone;
        this.email = email;
        this.permissor = permissor;
        this.permitido = permitido;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Apartamento getApartamento(){
        return apartamento;
    }

    public void setApartamento(Apartamento apartamento) {
        this.apartamento =apartamento;
    }

    public Morador getPermissor() {
        return permissor;
    }

    public void setPermissor(Morador permissor) {
        this.permissor = permissor;
    }

    public Boolean getPermitido() {
        return permitido;
    }

    public void setPermitido(Boolean permitido) {
        this.permitido = permitido;
    }


}
