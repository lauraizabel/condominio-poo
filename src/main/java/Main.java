import dados.*;
import services.ReservaService;

import java.util.Date;

public class Main {
  public static void main(String[] args) {
    // SÓ TESTANDO PQ SIM

    // criando instância do servico de reserva
    ReservaService reservaService = new ReservaService();

    // criando objeto Reserva
    Reserva reserva = new Reserva(1, 1, new Date());

    // testando
      // save
    reservaService.save(reserva);
      // getById
//    Reserva reservaFetched = reservaService.getById(2);
      // update
//    reservaFetched.setDataReserva(new Date());
//    reservaService.update(reservaFetched);
      //getAll
//    List<Reserva> reservasFetched = reservaService.getAll();
      // delete
//      reservaService.deleteById(3);
  }
}
