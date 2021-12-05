import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Fornecedor;

public class FornecedorTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Fornecedor fornecedor = takeClasses.fornecedor();

    @Test
    public void TestClassNotNull() {
        assertNotNull(fornecedor);
    }

    /* Testando Getters */  
    @Test 
    public void TestGetterId() {
        assertEquals(takeClasses.getId(), fornecedor.getId());
    }

    @Test
    public void TestGetterNome() {
        assertEquals(takeClasses.getName(), fornecedor.getNome());
    }

    @Test
    public void TestGetterCnpj() {
        assertEquals(takeClasses.getCnpj(), fornecedor.getCnpj());
    }

    @Test
    public void TestGetterEndereco() {
        assertEquals(takeClasses.getEndereco(), fornecedor.getEndereco());
    }

    @Test
    public void TestGetterTelefone() {
        assertEquals(takeClasses.getPhone(), fornecedor.getTelefone());
    }

    @Test
    public void TestGetterEmail() {
        assertEquals(takeClasses.getEmail(), fornecedor.getEmail());
    }

    /* Testando Setters */ 
    @Test
    public void TestSetterNome() {
        String newNome = faker.name().firstName();
        fornecedor.setNome(newNome);
        assertEquals(newNome, fornecedor.getNome());
    }

    @Test
    public void TestSetterEndereco() {
        String newEndereco = faker.address().fullAddress();
        fornecedor.setEndereco(newEndereco);
        assertEquals(newEndereco, fornecedor.getEndereco());
    }

    @Test
    public void TestSetterTelefone() {
        String newTelefone = faker.phoneNumber().cellPhone();
        fornecedor.setTelefone(newTelefone);
        assertEquals(newTelefone, fornecedor.getTelefone());
    }

    @Test
    public void TestSetterEmail() {
        String newEmail = faker.internet().emailAddress();
        fornecedor.setEmail(newEmail);
        assertEquals(newEmail, fornecedor.getEmail());
    }
}
