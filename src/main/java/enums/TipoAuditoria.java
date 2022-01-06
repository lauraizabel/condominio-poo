package enums;

public enum TipoAuditoria {
    CRIADO("CRIANDO"),
    EDITADO("EDITANDO"),
    DELETADO("DELETANDO");

    private String auditoria;

    TipoAuditoria(String auditoria) {
        this.auditoria = auditoria;
    }

    public String getAuditoria() {
        return auditoria;
    }
}
