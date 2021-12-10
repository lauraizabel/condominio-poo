package utils;

public class validacao {
    
    public static void validaCpf(String cpf) throws Exception {
        if(!cpf.matches("^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$")) {
            throw new Exception("CPF INVÁLIDO");
        };
    }

    public static void validaCnpj(String cnpj) throws Exception {
        if(!cnpj.matches("\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}")) {
            throw new Exception("CNPJ INVÁLIDO");
        }
    }

    public static void validaEmail(String email) throws Exception {
        if(!email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
            throw new Exception("EMAIL INVÁLIDO");
        }
    }
}
