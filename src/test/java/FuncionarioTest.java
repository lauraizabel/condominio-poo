import com.github.javafaker.Faker;
import dados.Funcionario;
import org.junit.*;

import static org.junit.Assert.*;

public class FuncionarioTest {
    Faker faker = new Faker();
    private TakeClasses takeClasses = new TakeClasses();
    private Funcionario funcionario = takeClasses.funcionario();

    /* Testando se a classe não iniciou como null */
    @Test
    public void TestClassNotNull() {
        assertNotNull(funcionario);
    }

    /* Testando Getters */
    @Test
    public void TestGetterId() {
        assertEquals(takeClasses.getId(), funcionario.getId());
    }

    @Test
    public void TestGetterName() {
        assertEquals(takeClasses.getName(), funcionario.getNome());
    }

    @Test
    public void TestGetterPhone() {
        assertEquals(takeClasses.getPhone(), funcionario.getTelefone());
    }

    @Test
    public void TestGetterEmail() {
        assertEquals(takeClasses.getEmail(), funcionario.getEmail());
    }

    @Test
    public void TestGetterCargo() {
        assertEquals(takeClasses.getCargo(), funcionario.getCargo());
    }

    @Test
    public void TestGetterEndereco() {
        assertEquals(takeClasses.getEndereco(), funcionario.getEndereco());
    }

    @Test
    public void TestGetterSalario() {
        assertTrue(takeClasses.getSalario() == funcionario.getSalario());
    }

    /* Testando Setters */
    @Test
    public void TestSetCPF() {
        String newCpf = faker.random().toString();
        funcionario.setCpf(newCpf);
        assertEquals(newCpf, funcionario.getCpf());
    }

    @Test
    public void TestSetName() {
        String newName = faker.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }

    @Test
    public void TestSetPhone() {
        String newPhone = faker.phoneNumber().cellPhone();
        funcionario.setNome(newPhone);
        assertEquals(newPhone, funcionario.getNome());
    }

    @Test
    public void TestSetEmail() {
        String newEmail = faker.name().fullName() + "@gmail.com";
        funcionario.setNome(newEmail);
        assertEquals(newEmail, funcionario.getNome());
    }

    @Test
    public void TestSetCargo() {
        String newName = faker.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }
}
