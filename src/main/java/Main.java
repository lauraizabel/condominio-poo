import dados.Funcionario;
import dados.Fornecedor;
import services.FuncionarioService;
import services.ReservaService;
import utils.validacao;

import java.util.Date;

public class Main {
      public static void main(String[] args) throws Exception {
            // SÓ TESTANDO PQ SIM

            // criando instância do servico de reserva
            ReservaService reservaService = new ReservaService();
            FuncionarioService funcionarioService = new FuncionarioService();

            // criando objeto Reserva
            Funcionario funcionario = new Funcionario("Gabriel", "81971196766", "g@d.com", "702.373.774-03", "gerente",
                        1000.0, "rua marte", new Date(), new Date());

            Fornecedor fornecedor = new Fornecedor("Gabrielltda", "02.025.659/0001-98", "rua sol", "37258139", "f@.com");

            funcionarioService.save(funcionario);      
            // validacao.validaCpf(funcionario.getCpf());
            // validacao.validaEmail(funcionario.getEmail());
            // validacao.validaCnpj(fornecedor.getCnpj());
            // testando
            // save
            // reservaService.save(reserva);
            // getById
            // Reserva reservaFetched = reservaService.getById(2);
            // update
            // reservaFetched.setDataReserva(new Date());
            // reservaService.update(reservaFetched);
            // getAll
            // List<Reserva> reservasFetched = reservaService.getAll();
            // delete
            // reservaService.deleteById(3);
      }

}
