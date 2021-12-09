import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Apartamento;
import dados.Carro;
import dados.Morador;

public class ApartamentoTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Apartamento apartamento = takeClasses.apartamento();

    @Test
    public void TestClassNotNull() {
        assertNotNull(apartamento);
    }

    @Test
    public void TestGetBloco() {
        assertEquals(takeClasses.getApartamento().getBloco(), apartamento.getBloco());
    }

    @Test
    public void TestGetAndar() {
        assertEquals(takeClasses.getApartamento().getAndar(), apartamento.getAndar());
    }

    @Test
    public void TestGetNumApartamento() {
        assertEquals(takeClasses.getApartamento().getNumApartamento(), apartamento.getNumApartamento());
    }

    @Test
    public void TestGetMoradores() {
        assertEquals(takeClasses.getApartamento().getMoradores(), apartamento.getMoradores());
    }

    @Test
    public void TestGetCarros() {
        assertEquals(takeClasses.getApartamento().getCarros(), apartamento.getCarros());
    }

    @Test
    public void TestSetBloco() {
        String newBloco = faker.lorem().characters();
        apartamento.setBloco(newBloco);
        assertEquals(newBloco, apartamento.getBloco());
    }

    @Test
    public void TestSetAndar() {
        int newAndar = faker.random().nextInt(1,20);
        apartamento.setAndar(newAndar);
        assertEquals(newAndar, apartamento.getAndar());
    }

    @Test
    public void TestSetNumero() {
        int newNumero = faker.random().nextInt(1, 500);
        apartamento.setAndar(newNumero);
        assertEquals(newNumero, apartamento.getAndar());
    }

    @Test
    public void TestSetMoradores() {
        ArrayList<Morador> newMoradores = new ArrayList<>();
        Morador morador = takeClasses.morador();
        newMoradores.add(morador);
        apartamento.setMoradores(newMoradores);
        assertEquals(newMoradores, apartamento.getMoradores());
    }

    @Test
    public void TestSetCarros() {
        ArrayList<Carro> newCarros = new ArrayList<>();
        Carro carro = takeClasses.carro();
        newCarros.add(carro);
        apartamento.setCarros(newCarros);
        assertEquals(newCarros, apartamento.getCarros());
    }
}
