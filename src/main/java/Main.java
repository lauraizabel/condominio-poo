import business.*;
import dados.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {
      public static void main(String[] args) throws Exception {
//            FornecedorService fornecedorService = new FornecedorService();
            ProdutoService produtoService = new ProdutoService();
            FuncionarioService funcionarioService = new FuncionarioService();
            CompraService compraService = new CompraService();
//            AlmoxarifadoService almoxarifadoService = new AlmoxarifadoService();

//            Fornecedor fornecedor = new Fornecedor("fornecedor legal", "02.025.659/0001-98", "endereco legal", "2345345234", "fornecedor@email.com");
//            fornecedorService.save(fornecedor);
//            Produto produto = new Produto("sabao", fornecedor, "293475", 100, 0);
//            produtoService.save(produto);
//            Funcionario funcionario = new Funcionario("Gabriel", "81971196766", "g@d.com", "702.373.774-03", "gerente",
//                    1000.0, "rua marte", new Date(), new Date());
//            funcionarioService.save(funcionario);

//            Fornecedor fornecedor = fornecedorService.getById(3);
            Produto produto = produtoService.getById(6);
            Funcionario funcionario = funcionarioService.getById(3);



            // COMPRA
//            Compra compra = new Compra(produto, 1, new Date(), funcionario, 2.5);
//            compraService.save(compra);

//            Double media = compraService.valorMedioProduto(6, new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), new Date());
//            System.out.println(media);

            // ALMOXARIFADO
//            Produto almoxarifados = almoxarifadoService.getProdutoById(6);
//            System.out.println(almoxarifados.getQuantidade());

//            boolean removido = almoxarifadoService.removerProduto(produto, funcionario, 1);
//            System.out.println(removido);

//            int quantidadeEmEstoque = almoxarifadoService.getQuatidadeEmEstoque(6);
//            System.out.println(quantidadeEmEstoque);

//            Funcionario funcionarioQueRegistrou = almoxarifadoService.getFuncionario(13);
//            System.out.println(funcionarioQueRegistrou.getNome());
      }
}
