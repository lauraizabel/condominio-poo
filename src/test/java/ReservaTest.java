import static org.junit.Assert.*;

import com.github.javafaker.Faker;
import java.util.Date;

import org.junit.Test;

import dados.Reserva;

public class ReservaTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Reserva reserva = takeClasses.reserva();

    @Test
    public void TestClassNotNull() {
        assertNotNull(reserva);
    }

              // GETTERS
    @Test
    public void TesteGetIdEspaco(){
        assertEquals(takeClasses.getIdEspaco(), reserva.getIdEspaco());
    }

    @Test
    public void TesteGetIdApartamento(){
        assertEquals(takeClasses.getIdApApartamento(), reserva.getIdApartamento());
    }

    @Test
    public void TesteGetData(){
        assertEquals(takeClasses.getData(), reserva.getDataReserva());
    }

    //setters

    @Test
    public void TestSetIdEspaco(){
        Integer newIdEspaco = faker.random().nextInt(1, 100);
        reserva.setIdEspaco(newIdEspaco);
        assertEquals(newIdEspaco, reserva.getIdEspaco());
    }

    @Test
    public void TestSetIdApartamento(){
        Integer newIdApartamento = takeClasses.getIdApApartamento();
        reserva.setIdEspaco(newIdApartamento);
        assertEquals(newIdApartamento, reserva.getIdApartamento());
    }

    @Test
    public void TestSetData(){
        Date newData = faker.date().birthday();
        reserva.setDataReserva(newData);
        assertEquals(newData, reserva.getDataReserva());
    }

}
