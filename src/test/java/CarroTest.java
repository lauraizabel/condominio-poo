import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Carro;

public class CarroTest {
    Faker faker = new Faker();
    private TakeClasses takeClasses = new TakeClasses();
    private Carro carro = takeClasses.carro();
    
    @Test
    public void TestClassNotNull() {
        assertNotNull(carro);
    }

    /* Testando Getters */
    @Test
    public void TestGetterModelo() {
        assertEquals(takeClasses.getModelo(), carro.getModelo());
    }

    @Test
    public void TestGetterPlaca() {
        assertEquals(takeClasses.getPlaca(), carro.getPlaca());
    }

    /* Testando Setters */
    @Test
    public void TestSetterModelo() {
        String newModelo = faker.random().toString();
        carro.setModelo(newModelo);
        assertEquals(newModelo, carro.getModelo());
    }

    @Test
    public void TestSetterPlaca() {
         String newPlaca = faker.random().toString();
         carro.setPlaca(newPlaca);
         assertEquals(newPlaca, carro.getPlaca());
    }



}
