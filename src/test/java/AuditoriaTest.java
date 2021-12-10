import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Auditoria;

public class AuditoriaTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Auditoria auditoria = takeClasses.auditoria();
    
    @Test
    public void TestClassNotNull() {
        assertNotNull(auditoria);
    }

    /* Testando Getters */
    @Test
    public void TestGetterTabelaAlterada() {
        assertEquals(takeClasses.getTabelaAlterada(), auditoria.getTabelaAlterada());
    }

    @Test
    public void TestGetterDataAlteracao() {
        assertEquals(takeClasses.getData(), auditoria.getDataAlteracao());
    }

    /* Testando Setters */
    @Test
    public void TestSetterTabelaAlterada() {
        String newTabelaAlterada = faker.lorem().word();
        auditoria.setTabelaAlterada(newTabelaAlterada);
        assertEquals(newTabelaAlterada, auditoria.getTabelaAlterada());
    }

    @Test
    public void TestSetterDataAlteracao() {
        Date newDataAlteracao = faker.date().birthday();
        auditoria.setDataAlteracao(newDataAlteracao);
        assertEquals(newDataAlteracao, auditoria.getDataAlteracao());
    }
}
