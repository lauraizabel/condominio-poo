import business.*;
import dados.Compra;
import dados.Funcionario;
import dados.Fornecedor;
import dados.Produto;

import java.util.Date;

public class Main {
      public static void main(String[] args) throws Exception {
            FornecedorService fornecedorService = new FornecedorService();
            ProdutoService produtoService = new ProdutoService();
            FuncionarioService funcionarioService = new FuncionarioService();
            CompraService compraService = new CompraService();

//            Fornecedor fornecedor = new Fornecedor("fornecedor legal", "02.025.659/0001-98", "endereco legal", "2345345234", "fornecedor@email.com");
//            fornecedorService.save(fornecedor);
//            Produto produto = new Produto("sabao", fornecedor, "293475", 100, 0);
//            Funcionario funcionario = new Funcionario("Gabriel", "81971196766", "g@d.com", "702.373.774-03", "gerente",
//                    1000.0, "rua marte", new Date(), new Date());
//            funcionarioService.save(funcionario);

            Fornecedor fornecedor = fornecedorService.getById(1);
            Produto produto = produtoService.getById(2);
            Funcionario funcionario = funcionarioService.getById(3);

            System.out.println(fornecedor.getNome());
            System.out.println(produto.getNome());
            System.out.println(funcionario.getNome());

            // COMPRA
//            Compra compra = new Compra(produto, 200, new Date(), funcionario, 2.5);
//            compraService.save(compra);

            // ALMOXARIFADO
      }
}
