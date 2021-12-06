import static org.junit.Assert.*;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.AcessoPermitido;
import dados.Apartamento;

public class AcessoPermitidoTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    AcessoPermitido acessoPermitido = takeClasses.acessoPermitido();


    @Test
    public void TestClassNotNull() {
        assertNotNull(acessoPermitido);
    }

    // Getters
    @Test
    public void TesteGetIdAcesso() {
        assertEquals(takeClasses.getIdAcesso(), acessoPermitido.getIdAcesso());
        
    }

    @Test
    public void TesteGetApartamento() {
        assertEquals(takeClasses.getApartamento(), acessoPermitido.getApartamento());
    }
    
    @Test
    public void TesteIsPermitido() {
        assertEquals(takeClasses.isPermitido(), acessoPermitido.isPermitido());
    }

    //Setters
    @Test
    public void TesteSetApartamento() {
        Apartamento newPermisor = new Apartamento();
        acessoPermitido.setApartamento(newPermisor);
        assertEquals(newPermisor, acessoPermitido.getApartamento());
    }
    
    @Test
    public void TesteSetPermitido() {
        Boolean newPermitido = faker.bool().bool();
        acessoPermitido.setPermitido(newPermitido);
        assertEquals(newPermitido, acessoPermitido.isPermitido());
    }

    
}
