import business.*;
import dados.*;

public class Main {
      public static void main(String[] args) throws Exception {
            FornecedorService fornecedorService = new FornecedorService();

//            Fornecedor fornecedor = new Fornecedor("laura", "02.025.659/0001-98", "endereco da laura", "2345345234", "fornecedor@email.com");
//            fornecedor.setId(35);
            fornecedorService.getAllAuditory();

      }
}
