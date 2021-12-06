import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Espaco;

public class EspacoTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Espaco espaco = takeClasses.espaco();

    @Test
    public void TestClassNotNull() {
        assertNotNull(espaco);
    }

    /* Testando Getters */  
    @Test 
    public void TestGetterId() {
        assertEquals(takeClasses.getId(), espaco.getId());
    }

    @Test
    public void TestGetterNome() {
        assertEquals(takeClasses.getNomeEspaco(), espaco.getNome());
    }

    @Test
    public void TestGetterCapacidade() {
        assertEquals(takeClasses.getCapacidade(), espaco.getCapacidade());
    }

    @Test
    public void TestGetterOcupado() {
        assertEquals(takeClasses.isOcupado(), espaco.isOcupado());
    }

    @Test
    public void TestGetterCustoReserva() {
        assertEquals(takeClasses.getCustoReserva(), espaco.getCustoReserva());
    }

    /* Testando Setters */ 
    @Test
    public void TestSetterNome() {
        String newNome = faker.lorem().word();
        espaco.setNome(newNome);
        assertEquals(newNome, espaco.getNome());
    }

    @Test
    public void TestSetterCapacidade() {
        int newCapacidade = faker.random().nextInt(20, 1000);
        espaco.setCapacidade(newCapacidade);
        assertEquals(newCapacidade, espaco.getCapacidade());
    }

    @Test
    public void TestSetterOcupado() {
        boolean newOcupado = faker.bool().bool();
        espaco.setOcupado(newOcupado);
        assertEquals(newOcupado, espaco.isOcupado());
    }

    @Test
    public void TestSetterCustoReserva() {
        Double newCustoReserva = faker.random().nextDouble();
        espaco.setCustoReserva(newCustoReserva);
        assertEquals(newCustoReserva, espaco.getCustoReserva());
    }
}
